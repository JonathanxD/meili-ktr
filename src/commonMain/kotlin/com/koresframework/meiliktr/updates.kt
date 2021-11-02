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

import com.koresframework.meiliktr.response.UpdateStatusResponse
import io.ktor.client.request.*
import io.ktor.http.*

class Updates(val meiliClient: MeiliClient) {
    /**
     * Gets the update status of a [update][updateId] in a [index][indexUid].
     */
    suspend fun getUpdateStatus(indexUid: String, updateId: Int): UpdateStatusResponse {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/updates/$updateId"
            }
        }
    }

    /**
     * Gets status of all running updates in the [index][indexUid].
     */
    suspend fun getAllUpdatesStatus(indexUid: String): List<UpdateStatusResponse> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/updates"
            }
        }
    }
}