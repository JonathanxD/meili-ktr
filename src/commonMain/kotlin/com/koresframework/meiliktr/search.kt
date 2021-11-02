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

import com.koresframework.meiliktr.request.SearchRequest
import com.koresframework.meiliktr.response.SearchResponse
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.reflect.*

class Search(val meiliClient: MeiliClient) {
    /**
     * Searches documents in the specified [index][indexUid].
     *
     * **Prefer the reified variant**.
     *
     * @param info Type information of [SearchResponse]. (Must be type info of [SearchResponse],
     * like `typeInfo<SearchResponse<Doc>>()`).
     */
    suspend fun <T> search(info: TypeInfo, indexUid: String, req: SearchRequest): SearchResponse<T> {
        val response = this.meiliClient.httpClient.post<HttpResponse> {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/search"
            }
            body = req
        }
        return response.call.receive(info) as SearchResponse<T>
    }

    /**
     * Searches documents in the specified [index][indexUid].
     */
    suspend inline fun <reified T> search(indexUid: String, req: SearchRequest): SearchResponse<T> {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/search"
            }
            body = req
        }
    }
}