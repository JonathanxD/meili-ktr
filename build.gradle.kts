val ktor_version: String by project
val kotlin_version: String by project
val kotlinx_json_serialization_version: String by project

plugins {
    kotlin("multiplatform") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    id("com.github.hierynomus.license") version "0.16.1"
    id("maven-publish")
}

group = "com.koresframework"
version = "0.1.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "16"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
        withJava()
    }
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    /*val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        
    }*/
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinx_json_serialization_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
                implementation("io.ktor:ktor-client-core:$ktor_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:$ktor_version")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("io.ktor:ktor-client-mock:$ktor_version")
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktor_version")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
            }
        }
        val nativeMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-curl:$ktor_version")
            }
        }
        val nativeTest by getting
    }
}

tasks {
    withType<nl.javadude.gradle.plugins.license.License> {
        header = rootProject.file("LICENSE")
        strictCheck = true

        this.setSource(fileTree("src").include("**/*.kt"))
    }
}

publishing {
    repositories {
        maven {
            // change to point to your repo, e.g. http://my.org/repo
            url = uri("$buildDir/repo")
        }
        maven {
            name = "GitLab"
            url = uri("https://gitlab.com/api/v4/projects/30890788/packages/maven")
            credentials(HttpHeaderCredentials::class) {
                val ciToken = System.getenv("CI_JOB_TOKEN")
                if (ciToken != null && ciToken.isNotEmpty()) {
                    name = "Job-Token"
                    value = System.getenv("CI_JOB_TOKEN")
                } else {
                    name = "Private-Token"
                    value = project.findProperty("GITLAB_TOKEN")?.toString() ?: System.getenv("GITLAB_TOKEN")
                }
            }
            authentication {
                val header by registering(HttpHeaderAuthentication::class)
            }
        }
    }
}