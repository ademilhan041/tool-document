package com.ailhan.tool.document.manager

import com.ailhan.tool.document.Content
import com.ailhan.tool.document.ContentData
import com.ailhan.tool.document.DocumentManager
import java.net.URL

class DummyDocumentManager : DocumentManager {
    override fun list(): List<URL> = listOf()

    override fun upload(content: Content): URL = URL("http://document")

    override fun download(filename: String): Content =
        Content("a.txt", "plain/text", ContentData.byte("a".toByteArray()))

    override fun delete(filename: String): Boolean = true
}
