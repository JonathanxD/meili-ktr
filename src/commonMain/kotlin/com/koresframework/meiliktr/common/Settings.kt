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
@file:UseSerializers(ZonedDateTimeSerializer::class)
package com.koresframework.meiliktr.common

import com.koresframework.meiliktr.serializers.SynonymsSerializer
import com.koresframework.meiliktr.serializers.ZonedDateTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class Settings(
    val displayedAttributes: List<String> = listOf("*"),
    val distinctAttribute: String? = null,
    val filterableAttributes: List<String> = emptyList(),
    val rankingRules: List<String> = emptyList(),
    val searchableAttributes: List<String> = listOf("*"),
    val sortableAttributes: List<String> = emptyList(),
    val stopWords: List<String> = emptyList(),
    val synonyms: Synonyms = Synonyms(emptyMap()),
)

@Serializable(with = SynonymsSerializer::class)
class Synonyms(val value: Map<String, List<String>>)