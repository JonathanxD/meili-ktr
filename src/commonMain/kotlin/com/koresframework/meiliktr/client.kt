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

import com.koresframework.meiliktr.http.client
import com.koresframework.meiliktr.request.IndexCreateRequest
import com.koresframework.meiliktr.request.IndexUpdateRequest
import com.koresframework.meiliktr.request.SearchRequest
import com.koresframework.meiliktr.response.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.reflect.TypeInfo

class MeiliClientConfig(val host: String, val port: Int, val apiKey: String? = null)

class MeiliClient(config: MeiliClientConfig) {
    val client = client(config)
    val documents = Documents(this)
    val updates = Updates(this)
    val search = Search(this)

    suspend fun createIndex(uid: String, primaryKey: String? = null): IndexResponse {
        return this.client.post {
            url("/indexes")
            body = IndexCreateRequest(uid, primaryKey)
        }
    }

    suspend fun updateIndex(uid: String, primaryKey: String? = null): IndexResponse {
        return this.client.put {
            url {
                encodedPath = "/indexes"
            }
            body = IndexUpdateRequest(uid, primaryKey)
        }
    }

    suspend fun deleteIndex(uid: String): DeleteResponse {
        val delete = this.client.delete<HttpResponse> {
            url {
                encodedPath = "/indexes/${uid.encodeURLPath()}"
            }
        }

        return DeleteResponse(uid, delete.status == HttpStatusCode.NoContent)
    }

    suspend fun indexes(): List<IndexResponse> {
        return this.client.get {
            url {
                encodedPath = "/indexes"
            }
        }
    }

    suspend fun index(uid: String): IndexResponse {
        return this.client.get {
            url {
                encodedPath = "/indexes/${uid.encodeURLPath()}"
            }
        }
    }

    class Documents(val meiliClient: MeiliClient) {
        suspend fun document(info: TypeInfo, indexUid: String, documentId: String): Any {
            val response = this.meiliClient.client.get<HttpResponse> {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents/${documentId.encodeURLPath()}"
                }
            }
            return response.call.receive(info)
        }

        suspend inline fun <reified T> document(indexUid: String, documentId: String): T {
            return this.meiliClient.client.get {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents/${documentId.encodeURLPath()}"
                }
            }
        }

        suspend fun documents(info: TypeInfo,
                             indexUid: String,
                             offset: Int = 0,
                             limit: Int = 20,
                             attributesToRetrieve: String = "*"): Any {
            val response = this.meiliClient.client.get<HttpResponse> {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
                    parameter("offset", offset)
                    parameter("limit", limit)
                    parameter("attributesToRetrieve", attributesToRetrieve)
                }
            }
            return response.call.receive(info)
        }

        suspend inline fun <reified T> documents(indexUid: String,
                                                 offset: Int = 0,
                                                 limit: Int = 20,
                                                 attributesToRetrieve: String = "*"): List<T> {
            return this.meiliClient.client.get {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
                    parameter("offset", offset)
                    parameter("limit", limit)
                    parameter("attributesToRetrieve", attributesToRetrieve)
                }
            }
        }

        suspend fun addDocuments(indexUid: String,
                                 documents: List<Any>,
                                 primaryKey: String? = null): UpdateDocumentResponse {
            return this.meiliClient.client.post {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
                    if (primaryKey != null) {
                        parameter("primaryKey", primaryKey)
                    }
                }
                body = documents
            }
        }

        suspend fun updateDocuments(indexUid: String,
                                 documents: List<Any>,
                                 primaryKey: String? = null): UpdateDocumentResponse {
            return this.meiliClient.client.put {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
                    if (primaryKey != null) {
                        parameter("primaryKey", primaryKey)
                    }
                }
                body = documents
            }
        }

        suspend fun deleteAllDocuments(indexUid: String): UpdateDocumentResponse {
            return this.meiliClient.client.delete {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
                }
            }
        }

        suspend fun deleteDocument(indexUid: String, documentId: String): UpdateDocumentResponse {
            return this.meiliClient.client.delete {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents/${documentId.encodeURLPath()}"
                }
            }
        }

        suspend fun deleteDocuments(indexUid: String, documentIds: List<Any>): UpdateDocumentResponse {
            return this.meiliClient.client.delete {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents/delete-batch"
                }
                body = documentIds
            }
        }
    }

    class Updates(val meiliClient: MeiliClient) {
        suspend fun updateStatus(indexUid: String, updateId: Int): UpdateResponse {
            return this.meiliClient.client.get {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/updates/$updateId"
                }
            }
        }

        suspend fun allUpdatesStatus(indexUid: String): List<UpdateResponse> {
            return this.meiliClient.client.get {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/updates"
                }
            }
        }
    }

    class Search(val meiliClient: MeiliClient) {
        suspend fun <T> search(info: TypeInfo, indexUid: String, req: SearchRequest): SearchResponse<T> {
            val response = this.meiliClient.client.post<HttpResponse> {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/search"
                }
                body = req
            }
            return response.call.receive(info) as SearchResponse<T>
        }

        suspend inline fun <reified T> search(indexUid: String, req: SearchRequest): SearchResponse<T> {
            return this.meiliClient.client.post {
                url {
                    encodedPath = "/indexes/${indexUid.encodeURLPath()}/search"
                }
                body = req
            }
        }
    }
}