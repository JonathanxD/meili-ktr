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
import io.ktor.client.request.*
import io.ktor.http.*

class Settings(val meiliClient: MeiliClient) {
    suspend fun settings(indexUid: String): Settings {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings"
            }
        }
    }

    suspend fun updateSettings(indexUid: String, settings: Settings): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings"
            }
            body = settings
        }
    }

    suspend fun resetSettings(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings"
            }
        }
    }

    // -- Specific -- [displayed-attributes]
    suspend fun displayedAttributes(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/displayed-attributes"
            }
        }
    }

    suspend fun updateDisplayedAttributes(indexUid: String, displayedAttributes: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/displayed-attributes"
            }
            body = displayedAttributes
        }
    }

    suspend fun resetDisplayedAttributes(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/displayed-attributes"
            }
        }
    }

    // -- Specific -- [distinct-attribute]
    suspend fun distinctAttribute(indexUid: String): String {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/distinct-attribute"
            }
        }
    }

    suspend fun updateDistinctAttribute(indexUid: String, distinctAttribute: String): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/distinct-attribute"
            }
            body = distinctAttribute
        }
    }

    suspend fun resetDistinctAttribute(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/distinct-attribute"
            }
        }
    }

    // -- Specific -- [filterable-attributes]
    suspend fun filterableAttributes(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/filterable-attributes"
            }
        }
    }

    suspend fun updateFilterableAttributes(indexUid: String, filterableAttributes: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/filterable-attributes"
            }
            body = filterableAttributes
        }
    }

    suspend fun resetFilterableAttributes(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/filterable-attributes"
            }
        }
    }

    // -- Specific -- [ranking-rules]
    suspend fun rankingRules(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/ranking-rules"
            }
        }
    }

    suspend fun updateRankingRules(indexUid: String, rankingRules: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/ranking-rules"
            }
            body = rankingRules
        }
    }

    suspend fun resetRankingRules(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/ranking-rules"
            }
        }
    }

    // -- Specific -- [searchable-attributes]
    suspend fun searchableAttributes(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/searchable-attributes"
            }
        }
    }

    suspend fun updateSearchableAttributes(indexUid: String, searchableAttributes: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/searchable-attributes"
            }
            body = searchableAttributes
        }
    }

    suspend fun resetSearchableAttributes(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/searchable-attributes"
            }
        }
    }

    // -- Specific -- [sortable-attributes]
    suspend fun sortableAttributes(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/sortable-attributes"
            }
        }
    }

    suspend fun updateSortableAttributes(indexUid: String, sortableAttributes: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/sortable-attributes"
            }
            body = sortableAttributes
        }
    }

    suspend fun resetSortableAttributes(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/sortable-attributes"
            }
        }
    }

    // -- Specific -- [stop-words]
    suspend fun stopWords(indexUid: String): List<String> {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/stop-words"
            }
        }
    }

    suspend fun updateStopWords(indexUid: String, stopWords: List<String>): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/stop-words"
            }
            body = stopWords
        }
    }

    suspend fun resetStopWords(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/stop-words"
            }
        }
    }

    // -- Specific -- [synonyms]
    suspend fun synonyms(indexUid: String): Synonyms {
        return this.meiliClient.httpClient.get {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/synonyms"
            }
        }
    }

    suspend fun updateSynonyms(indexUid: String, synonyms: Synonyms): UpdateResponse {
        return this.meiliClient.httpClient.post {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/synonyms"
            }
            body = synonyms
        }
    }

    suspend fun resetSynonyms(indexUid: String): UpdateResponse {
        return this.meiliClient.httpClient.delete {
            url {
                encodedPath = "/indexes/${indexUid.encodeURLPath()}/settings/synonyms"
            }
        }
    }
}