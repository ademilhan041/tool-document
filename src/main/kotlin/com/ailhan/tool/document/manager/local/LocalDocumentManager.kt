package com.ailhan.tool.document.manager.local

import com.ailhan.tool.document.Content
import com.ailhan.tool.document.ContentData
import com.ailhan.tool.document.DocumentManager
import com.ailhan.tool.document.MimeTypeMap
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths

class LocalDocumentManager(config: DocumentLocalConfig) : DocumentManager {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val basePath = Paths.get(config.localBasePath)

    init {
        if (!Files.exists(basePath)) Files.createDirectories(basePath)
    }

    override fun list(): List<URL> {
        return basePath.toFile().listFiles()?.map { it.toURI().toURL() } ?: listOf()
    }

    override fun upload(content: Content): URL {
        val docPath = basePath.resolve(content.filename)
        if (!docPath.toFile().parentFile.exists()) {
            val foldersCreated = docPath.toFile().parentFile.mkdirs()
            if (!foldersCreated) throw RuntimeException("DOCUMENT_FOLDER_CREATE_ERROR")
        }
        if (content.data.byte != null && content.data.byte.isNotEmpty()) {
            Files.copy(ByteArrayInputStream(content.data.byte), docPath)
        } else {
            Files.copy(content.data.stream!!, docPath)
            content.data.close()
        }
        logger.info("${content.filename} has been uploaded to Local folder[$basePath].")
        return docPath.toUri().toURL()
    }

    override fun download(filename: String): Content {
        val docPath = basePath.resolve(filename)
        val type = MimeTypeMap.mimeTypeFromExtension(docPath.toFile().extension) ?: ""
        return Content(docPath.toString(), type, ContentData.stream(docPath.toFile().inputStream()))
    }

    override fun delete(filename: String): Boolean {
        val docPath = basePath.resolve(filename)
        return try {
            Files.delete(docPath)
            !Files.exists(docPath)
        } catch (e: NoSuchFileException) {
            false
        }
    }
}
