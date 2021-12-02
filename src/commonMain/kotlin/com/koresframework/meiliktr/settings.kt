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

import com.koresframework.meiliktr.common.Settings
import com.koresframework.meiliktr.common.Synonyms
import com.koresframework.meiliktr.response.UpdateResponse
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class Settings(val meiliClient: MeiliClient) {
    suspend fun getSettings(indexUid: String): Settings {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings"
            }
        }.body()
    }

    suspend fun updateSettings(indexUid: String, settings: Settings): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings"
            }
            setBody(settings)
        }.body()
    }

    suspend fun resetSettings(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings"
            }
        }.body()
    }

    // -- Specific -- [displayed-attributes]
    suspend fun getDisplayedAttributes(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/displayed-attributes"
            }
        }.body()
    }

    suspend fun updateDisplayedAttributes(indexUid: String, displayedAttributes: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/displayed-attributes"
            }
            setBody(displayedAttributes)
        }.body()
    }

    suspend fun resetDisplayedAttributes(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/displayed-attributes"
            }
        }.body()
    }

    // -- Specific -- [distinct-attribute]
    suspend fun getDistinctAttribute(indexUid: String): String {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/distinct-attribute"
            }
        }.body()
    }

    suspend fun updateDistinctAttribute(indexUid: String, distinctAttribute: String): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/distinct-attribute"
            }
            setBody(distinctAttribute)
        }.body()
    }

    suspend fun resetDistinctAttribute(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/distinct-attribute"
            }
        }.body()
    }

    // -- Specific -- [filterable-attributes]
    suspend fun getFilterableAttributes(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/filterable-attributes"
            }
        }.body()
    }

    suspend fun updateFilterableAttributes(indexUid: String, filterableAttributes: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/filterable-attributes"
            }
            setBody(filterableAttributes)
        }.body()
    }

    suspend fun resetFilterableAttributes(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/filterable-attributes"
            }
        }.body()
    }

    // -- Specific -- [ranking-rules]
    suspend fun getRankingRules(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/ranking-rules"
            }
        }.body()
    }

    suspend fun updateRankingRules(indexUid: String, rankingRules: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/ranking-rules"
            }
            setBody(rankingRules)
        }.body()
    }

    suspend fun resetRankingRules(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/ranking-rules"
            }
        }.body()
    }

    // -- Specific -- [searchable-attributes]
    suspend fun getSearchableAttributes(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/searchable-attributes"
            }
        }.body()
    }

    suspend fun updateSearchableAttributes(indexUid: String, searchableAttributes: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/searchable-attributes"
            }
            setBody(searchableAttributes)
        }.body()
    }

    suspend fun resetSearchableAttributes(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/searchable-attributes"
            }
        }.body()
    }

    // -- Specific -- [sortable-attributes]
    suspend fun getSortableAttributes(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/sortable-attributes"
            }
        }.body()
    }

    suspend fun updateSortableAttributes(indexUid: String, sortableAttributes: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/sortable-attributes"
            }
            setBody(sortableAttributes)
        }.body()
    }

    suspend fun resetSortableAttributes(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/sortable-attributes"
            }
        }.body()
    }

    // -- Specific -- [stop-words]
    suspend fun getStopWords(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/stop-words"
            }
        }.body()
    }

    suspend fun updateStopWords(indexUid: String, stopWords: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/stop-words"
            }
            setBody(stopWords)
        }.body()
    }

    suspend fun resetStopWords(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/stop-words"
            }
        }.body()
    }

    // -- Specific -- [synonyms]
    suspend fun getSynonyms(indexUid: String): Synonyms {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/synonyms"
            }
        }.body()
    }

    suspend fun updateSynonyms(indexUid: String, synonyms: Synonyms): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/synonyms"
            }
            setBody(synonyms)
        }.body()
    }

    suspend fun resetSynonyms(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/synonyms"
            }
        }.body()
    }
}