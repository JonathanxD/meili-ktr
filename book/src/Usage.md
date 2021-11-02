# Usage

To use meili-ktr, first add the following repository to your project:

```kotlin
maven("https://gitlab.com/api/v4/projects/30890788/packages/maven")
```

Then add the meili-ktr dependency to your dependencies section:

```kotlin
implementation("com.koresframework:meili-ktr:$meili_ktr_version")
```

And chose one Engine to use with Ktor Client, you can find all engines in the [official documentation](https://ktor.io/docs/http-client-engines.html),
for example, the CIO Engine:

```kotlin
implementation("io.ktor:ktor-client-cio:$ktor_version")
```