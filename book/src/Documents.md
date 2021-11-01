# Indexes

## Get document

```kotlin
@Serializable
data class Doc(val docId: String, val text: String)

val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val doc: Doc = client.documents.document(indexUid = "docs", documentId = "0")
```

## Get documents
```kotlin
@Serializable
data class Doc(val docId: String, val text: String)

val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val doc: List<Doc> = client.documents.documents(indexUid = "docs", /* optional */ offset = 0, /* optional */ limit = 20, /* optional */ attributesToRetrieve = "*")
```

## Add documents
```kotlin
@Serializable
data class Doc(val docId: String, val text: String)

val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))

val firstDoc = Doc("0", "Lorem ipsum")
val secondDoc = Doc("1", "Dolor sit amet")

val addDocuments = client.documents.addDocuments(indexUid = "docs", documents = listOf(firstDoc, secondDoc))
```

## Update documents
```kotlin
@Serializable
data class Doc(val docId: String, val text: String)

val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))

val firstDoc = Doc("0", "Dolor sit amet")
val secondDoc = Doc("1", "Lorem ipsum")

val updateDocuments = client.documents.updateDocuments(indexUid = "docs", documents = listOf(firstDoc, secondDoc))
```

## Delete document by id

```kotlin
val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val deleteDocument = client.documents.deleteDocument(indexUid = "docs", documentId = "1")
```

## Delete multiple documents by id

```kotlin
val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val deleteDocuments = client.documents.deleteDocuments(indexUid = "docs", documentIds = listOf("0", "1"))
```

## Delete all documents

```kotlin
val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val deleteDocuments = client.documents.deleteAllDocuments(indexUid = "docs")
```
