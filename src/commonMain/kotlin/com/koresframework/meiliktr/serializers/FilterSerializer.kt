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
package com.koresframework.meiliktr.serializers

import com.koresframework.meiliktr.request.Filter
import com.koresframework.meiliktr.request.FilterQuery
import com.koresframework.meiliktr.request.Filters1Query
import com.koresframework.meiliktr.request.Filters2Query
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(Filter::class)
class FilterSerializer : KSerializer<Filter> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("com.koresframework.meiliktr.request.Filter", PrimitiveKind.STRING)

    val listSerial = ListSerializer(String.serializer())
    val listListSerial = ListSerializer(listSerial)

    override fun deserialize(decoder: Decoder): Filter =
        TODO()

    override fun serialize(encoder: Encoder, value: Filter) {
        when (value) {
            is FilterQuery -> {
                encoder.encodeString(value.filter)
            }
            is Filters1Query -> {
                listSerial.serialize(encoder, value.filters)
            }
            is Filters2Query -> {
                listListSerial.serialize(encoder, value.filters)
            }
            else -> {
                TODO()
            }
        }
    }
}