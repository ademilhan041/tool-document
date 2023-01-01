package com.ailhan.tool.document

import java.net.URL

interface DocumentManager {
    fun list(): List<URL>

    fun upload(content: Content): URL

    fun download(filename: String): Content

    fun delete(filename: String): Boolean
}
