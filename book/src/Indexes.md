# Indexes

## Get indexes

```kotlin
val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val indexes = client.indexes.getIndexes()
```

## Create index
```kotlin
val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val createIndex = client.indexes.createIndex("docs", primaryKey = "docId")
```

## Delete index
```kotlin
val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val deleteIndex = client.indexes.deleteIndex("docs")
```

## Update index
```kotlin
val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val updateIndex = client.indexes.updateIndex("docs", primaryKey = "docId")
```

## Get Index
```kotlin
val client = MeiliClient(MeiliClientConfig(host = "localhost", port = 7700, apiKey = null))
val index = client.indexes.getIndex("docs")
```
