package com.ailhan.tool.document.manager.local

import java.util.*

/**
 * Available Properties
 *
 * doc.local.basePath
 */
class DocumentLocalConfig(props: Properties) {
    var localBasePath: String = props.getProperty("doc.local.basePath")

    init {
        if (localBasePath.isEmpty()) throw IllegalArgumentException("DOC_LOCAL_PATH_MISSING")
    }
}
