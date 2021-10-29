![Logo](logo.png)

![MIT License](https://img.shields.io/badge/License-MIT-green)
[![Discord](https://img.shields.io/discord/291407467286364164.svg)](https://discord.gg/3cQWmtj)
[![Pipeline](https://img.shields.io/gitlab/pipeline-status/Kores/meili-ktr)](https://gitlab.com/Kores/meili-ktr/-/pipelines)
[![Packages](https://img.shields.io/gitlab/v/release/Kores/meili-ktr)](https://gitlab.com/Kores/meili-ktr/-/packages)


Multiplatform Kotlin client for MeiliSearch API.

**It is under heavy development and uses Ktor Client framework to make REST calls to MeiliSearch API.** 

## Coroutine Based

Meili Ktr is fully coroutine-based and multi-platform, it does support Koltin JS, Kotlin JVM and Kotlin/Native.

## Not Java-friendly

meili-ktr is designed for Kotlin language and is not Java friendly, for a Java library uses the Meili official Java library:
[MeiliSearch Java](https://github.com/meilisearch/meilisearch-java).

## Examples

### Create, Delete and Get indices

```kotlin
val client = MeiliClient(MeiliClientConfiguration("localhost", 7700))
val createIndex = client.createIndex("articles")
val indexes = client.indexes()
val deleteIndex = client.deleteIndex("articles")
```

### Add document

```kotlin
@Serializable
data class Doc(val docId: String, val text: String)

val client = MeiliClient(MeiliClientConfiguration("localhost", 7700))
val createIndex = client.createIndex("docs", "docId")
val addDoc = client.documents.addDocuments("docs", listOf(Doc("1", "Oh my MeiliSearch")))
```

### Get documents

```kotlin
@Serializable
data class Doc(val docId: String, val text: String)

val client = MeiliClient(MeiliClientConfiguration("localhost", 7700))
val docs = client.documents.documents<Doc>("docs")
```

### Delete document

```kotlin
val client = MeiliClient(MeiliClientConfiguration("localhost", 7700))
client.documents.deleteDocument("docs", "1")
```

### Search documents

```kotlin
@Serializable
data class Doc(val docId: String, val text: String)

val client = MeiliClient(MeiliClientConfiguration("localhost", 7700))
val s = client.search.search<Doc>("docs", SearchRequest(q = "MeiliSearch"))
```

### Wait operation finish

```kotlin
@Serializable
data class Doc(val docId: String, val text: String)

val client = MeiliClient(MeiliClientConfiguration("localhost", 7700))
val addDoc = client.documents.addDocuments("docs", listOf(Doc("1", "Oh my MeiliSearch")))
client.updates.delayUntilProcessed("docs", addDoc.updateId)
```