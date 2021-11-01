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
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.reflect.*

class Documents(val meiliClient: MeiliClient) {
    suspend fun document(info: TypeInfo, indexUid: String, documentId: String): Any {
        val response = this.meiliClient.httpClient.get<HttpResponse> {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents/${documentId.encodeURLPath()}"
            }
        }
        return response.call.receive(info)
    }

    suspend inline fun <reified T> document(indexUid: String, documentId: String): T {
        return this.meiliClient.httpClient.get {
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
        val response = this.meiliClient.httpClient.get<HttpResponse> {
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
        return this.meiliClient.httpClient.get {
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
                             primaryKey: String? = null): UpdateResponse {
        return this.meiliClient.httpClient.post {
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
                                primaryKey: String? = null): UpdateResponse {
        return this.meiliClient.httpClient.put {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
                if (primaryKey != null) {
                    parameter("primaryKey", primaryKey)
                }
            }
            body = documents
        }
    }

    suspend fun deleteAllDocuments(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents"
            }
        }
    }

    suspend fun deleteDocument(indexUid: String, documentId: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents/${documentId.encodeURLPath()}"
            }
        }
    }

    suspend fun deleteDocuments(indexUid: String, documentIds: List<Any>): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/documents/delete-batch"
            }
            body = documentIds
        }
    }
}