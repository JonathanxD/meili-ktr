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

import com.koresframework.meiliktr.response.DumpsResponse
import com.koresframework.meiliktr.response.DumpsStatusResponse
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class Dumps(val meiliClient: MeiliClient) {
    /**
     * Creates a `.dump` file in the MeiliSearch Server directory.
     *
     * To retrieve the status of the dump operation, use [getDumpStatus].
     */
    suspend fun dumps(): DumpsResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/dumps"
            }
        }.body()
    }

    /**
     * Retrieves the status of a [dump][dumpUid] operation.
     */
    suspend fun getDumpStatus(dumpUid: String): DumpsStatusResponse {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/dumps/${dumpUid.encodeURLPath()}/status"
            }
        }.body()
    }
}