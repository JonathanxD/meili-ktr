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

import com.koresframework.meiliktr.util.twoDigit

expect class ZonedDateTime(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int,
    nano: Int,
    offset: ZoneOffset
) {
    val year: Int
    val month: Int
    val day: Int
    val hour: Int
    val minute: Int
    val second: Int
    val nano: Int
    val offset: ZoneOffset

    fun toRepresentation(): String

    companion object {
        fun from(representation: String): ZonedDateTime
    }
}

fun ZonedDateTime.render() =
    "${year.twoDigit()}-${month.twoDigit()}-${day.twoDigit()}T${hour.twoDigit()}:${minute.twoDigit()}:${second.twoDigit()}.$nano${offset.id}"

object CommonParser {
    fun parse(representation: String): ZonedDateTime {
        var year: Int? = null
        var month: Int? = null
        var day: Int? = null
        var hour: Int? = null
        var minute: Int? = null
        var second: Int? = null
        var nano: Int? = null
        var offset: String? = null

        val sb = StringBuilder()

        for (ch in representation) {
            if (ch == '-') {
                if (year == null) {
                    year = sb.toString().toInt()
                    sb.setLength(0)
                } else if (month == null) {
                    month = sb.toString().toInt()
                    sb.setLength(0)
                }
            } else if (ch == 'T') {
                if (day == null) {
                    day = sb.toString().toInt()
                    sb.setLength(0)
                }
            } else if (ch == ':') {
                if (hour == null) {
                    hour = sb.toString().toInt()
                    sb.setLength(0)
                } else if (minute == null) {
                    minute = sb.toString().toInt()
                    sb.setLength(0)
                }
            } else if (ch == '.') {
                if (second == null) {
                    second = sb.toString().toInt()
                    sb.setLength(0)
                }
            } else if (ch == '+' || ch == '-' || ch == 'Z') {
                if (nano == null) {
                    nano = sb.toString().toInt()
                }
                sb.setLength(0)
                sb.append(ch)
            } else {
                sb.append(ch)
            }
        }

        offset = sb.toString()

        return ZonedDateTime(year!!, month!!, day!!, hour!!, minute!!, second!!, nano!!, ZoneOffset(offset))
    }
}