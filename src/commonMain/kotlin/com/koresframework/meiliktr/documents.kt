/**
 * meili-ktr - Multiplatform Kotlin client for MeiliSearch API.
 * Copyright 2021 JonathanxD <jhrldev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.koresframework.meiliktr

import com.koresframework.meiliktr.response.UpdateResponse
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.reflect.*
import io.ktor.client.request.get

class Documents(val meiliClient: MeiliClient) {
    /**
     * Get a document by its [documentId] in the specified [index][indexUid].
     *
     * **Prefer the reified variant.**
     *
     * @param info The type information of the document type.
     */
    suspend fun getDocument(info: TypeInfo, indexUid: String, documentId: String): Any {
        val response = this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents/${documentId.encodeURLPath()}"
            }
        }
        return response.call.body(info)
    }

    /**
     * Get a document by its [documentId] in the specified [index][indexUid].
     */
    suspend inline fun <reified T> getDocument(indexUid: String, documentId: String): T {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents/${documentId.encodeURLPath()}"
            }
        }.body()
    }

    /**
     * Gets all documents of specified [index][indexUid].
     *
     * **Prefer the reified variant.**
     *
     * @param info Type information of the List of document type (must be a List, like `typeInfo<List<Doc>>`).
     */
    suspend fun getDocuments(info: TypeInfo,
                             indexUid: String,
                             offset: Int = 0,
                             limit: Int = 20,
                             attributesToRetrieve: String = "*"): Any {
        val response = this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
                parameter("offset", offset)
                parameter("limit", limit)
                parameter("attributesToRetrieve", attributesToRetrieve)
            }
        }
        return response.call.body(info)
    }

    /**
     * Gets all documents of specified [index][indexUid].
     */
    suspend inline fun <reified T> getDocuments(indexUid: String,
                                                offset: Int = 0,
                                                limit: Int = 20,
                                                attributesToRetrieve: String = "*"): List<T> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
                parameter("offset", offset)
                parameter("limit", limit)
                parameter("attributesToRetrieve", attributesToRetrieve)
            }
        }.body()
    }

    /**
     * Adds all documents of the specified [documents] list to the [index][indexUid].
     *
     * @param primaryKey Primary key of documents.
     */
    suspend fun addDocuments(indexUid: String,
                             documents: List<Any>,
                             primaryKey: String? = null): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
                if (primaryKey != null) {
                    parameter("primaryKey", primaryKey)
                }
            }
            setBody(documents)
        }.body()
    }

    /**
     * Updates all documents of the specified [documents] list in the [index][indexUid].
     *
     * @param primaryKey Primary key of documents.
     */
    suspend fun updateDocuments(indexUid: String,
                                documents: List<Any>,
                                primaryKey: String? = null): UpdateResponse {
        return this.meiliClient.httpClient.put {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
                if (primaryKey != null) {
                    parameter("primaryKey", primaryKey)
                }
            }
            setBody(documents)
        }.body()
    }

    /**
     * Deletes all documents of the [index][indexUid].
     */
    suspend fun deleteAllDocuments(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
            }
        }.body()
    }

    /**
     * Deletes a specific [document][documentId] of the [index][indexUid]
     */
    suspend fun deleteDocument(indexUid: String, documentId: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents/${documentId.encodeURLPath()}"
            }
        }.body()
    }

    /**
     * Deletes all documents with id matching provided [documentIds] list of the [index][indexUid].
     */
    suspend fun deleteDocuments(indexUid: String, documentIds: List<Any>): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents/delete-batch"
            }
            setBody(documentIds)
        }.body()
    }
}