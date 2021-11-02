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
version = "0.1.4"

repositories {
    mavenCentral()
}

val hostOs = System.getProperty("os.name")
val isLinux = hostOs.equals("linux", ignoreCase = true)

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
    val macos = macosX64("macos64")
    val linux = linuxX64("linux64")
    val windows = mingwX64("mingw64")

    configure(listOf(macos, linux, windows)) {
    }

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

        }
        val jvmTest by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:$ktor_version")
                implementation("io.ktor:ktor-client-mock:$ktor_version")
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
        }
        val jsTest by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktor_version")
                implementation(kotlin("test-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
            }
        }
        val macos64Main by getting {

        }
        val linux64Main by getting {

        }
        val mingw64Main by getting {

        }
        val macos64Test by getting
        val linux64Test by getting {
            if (isLinux) {
                dependencies {
                    implementation("io.ktor:ktor-client-curl:$ktor_version")
                }
            }
        }
        val mingw64Test by getting

        val nativeMain by sourceSets.creating {
            dependsOn(commonMain)
            macos64Main.dependsOn(this)
            linux64Main.dependsOn(this)
            mingw64Main.dependsOn(this)
        }
        val nativeTest by sourceSets.creating {
            dependsOn(commonTest)
            macos64Test.dependsOn(this)
            linux64Test.dependsOn(this)
            mingw64Test.dependsOn(this)
        }
    }
    val publicationsFromMainHost =
        listOf(jvm(), js(), linux).map { it.name } + "kotlinMultiplatform"

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

        publications {
            matching { it.name in publicationsFromMainHost }.all {
                val targetPublication = this@all
                tasks.withType<AbstractPublishToMaven>()
                    .matching { it.publication == targetPublication }
                    .configureEach { onlyIf { isLinux } }
            }
        }
    }
}

tasks {
    withType<nl.javadude.gradle.plugins.license.License> {
        header = rootProject.file("LICENSE")
        strictCheck = true

        this.setSource(fileTree("src").include("**/*.kt"))
    }
}
