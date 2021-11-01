# Client

```kotlin
val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
```

## Accessing Http Client

```kotlin
val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val httpClient = client.httpClient
```