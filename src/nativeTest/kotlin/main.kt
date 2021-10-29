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
import com.koresframework.meiliktr.MeiliClient
import com.koresframework.meiliktr.MeiliClientConfig
import com.koresframework.meiliktr.request.SearchRequest
import com.koresframework.meiliktr.util.delayUntilProcessed
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Serializable
data class Doc(val docId: String, val text: String)

/**
 * A generic test, ignored by default until we properly configure MeiliSearch launch then test in CI/CD.
 */
class GenericTest {
    @Test
    @Ignore
    fun `test create delete insert and delete doc`() = runBlocking {
        val client = MeiliClient(MeiliClientConfig("localhost", 7700))
        client.indexes().filter {
            it.uid == "docs"
        }.forEach {
            client.deleteIndex(it.uid)
        }
        val create = client.createIndex("docs", "docId")
        assertEquals("docs", create.uid)
        val insert = client.documents.addDocuments("docs", listOf(Doc("10", "Hello my dear world")))
        val insert2 = client.documents.addDocuments("docs", listOf(Doc("101", " my dear world")))
        client.updates.delayUntilProcessed("docs", insert.updateId)
        client.updates.delayUntilProcessed("docs", insert2.updateId)
        val docs = client.documents.documents<Doc>("docs")
        assertEquals(2, docs.size)

        val s = client.search.search<Doc>("docs", SearchRequest(q = "hello"))

        assertEquals(1, s.hits.size)
        assertEquals("10", s.hits.single().docId)
    }
}
