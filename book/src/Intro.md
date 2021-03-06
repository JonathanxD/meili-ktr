<div align="center"><img alt="Logo" src="./logo.png"></div>

## Description

A [MeiliSearch](https://meilisearch.com) API for Kotlin, which takes advantage of Kotlin's powerful Coroutines feature and Ktor Client for HTTP requests.

## Platform-Agnostic

meili-ktr is built using Kotlin MMP (Multiplatform Programming) and runs on JVM, Javascript (browser or NodeJS), or natively on any Kotlin/Native target that also have a Ktor Client Engine implementation and Kotlinx Serialization support.

Also, this library does not use a specific Ktor Client Engine, the user must choose the one that is supported in the targeting platform and suits its needs.

## Tiny and Low-level

It exposes the `HttpClient` used to make requests to MeiliSearch and provides low-level layer for communicating with Meili, through Http Protocol.

## FAQ? (or NEA-YET: no one ever asked... yet)

### Which Kotlin/Native targets are official supported?

These are the platforms meili-ktr is officially built:

- Windows-x86_64 (Shared Runner)
- Linux-x86_64 (Shared Runner)
- macOS-arm64 (Dedicated Runner)

And binaries are distributed through [GitLab Packages Maven Repository](https://gitlab.com/Kores/meili-ktr/-/packages).
However, this library does not use any platform-specific code, so it can be built on any target that supports Ktor Client Engine 
and Kotlinx Serialization. We just are not able to provide binaries for all targets supported by these libraries.

### Does meili-ktr has Unit Tests?

We have a low-level API with little to no business logic, and to do unit tests we need to start a MeiliSearch Server instance, so we do not have unit tests, yet... We are planing to start a MeiliSearch Server instance in CI/CD just for unit tests and then shutdown it.