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
package com.koresframework.meiliktr.time

import java.time.ZoneId
import java.time.format.DateTimeFormatter

actual class ZonedDateTime(
    val impl: java.time.ZonedDateTime
) {
    actual val year: Int = impl.year
    actual val month: Int = impl.monthValue
    actual val day: Int = impl.dayOfMonth
    actual val hour: Int = impl.hour
    actual val minute: Int = impl.minute
    actual val second: Int = impl.second
    actual val nano: Int = impl.nano
    actual val offset: ZoneOffset = ZoneOffset(impl.offset.id)

    actual constructor(year: Int,
            month: Int,
            day: Int,
            hour: Int,
            minute: Int,
            second: Int,
            nano: Int,
            offset: ZoneOffset
    ) : this(java.time.ZonedDateTime.of(year, month, day, hour, minute, second, nano, ZoneId.of(offset.id)))

    actual fun toRepresentation(): String =
        DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.impl)


    actual companion object {
        actual fun from(representation: String): ZonedDateTime =
            ZonedDateTime(java.time.ZonedDateTime.parse(representation, DateTimeFormatter.ISO_OFFSET_DATE_TIME))
    }

    override fun toString(): String = this.toRepresentation()
}