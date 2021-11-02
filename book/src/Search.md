# Search

```kotlin
@Serializable
data class Doc(val docId: String, val text: String)

val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val search: SearchResponse<Doc> = client.search.search("docs", SearchRequest(q = "Lorem"))
```