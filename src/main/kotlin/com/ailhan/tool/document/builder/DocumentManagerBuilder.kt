package com.ailhan.tool.document.builder

import com.ailhan.tool.document.DocumentManager
import com.ailhan.tool.document.manager.DummyDocumentManager
import com.ailhan.tool.document.manager.aws.AwsDocumentManager
import com.ailhan.tool.document.manager.aws.DocumentAwsConfig
import com.ailhan.tool.document.manager.local.DocumentLocalConfig
import com.ailhan.tool.document.manager.local.LocalDocumentManager

class DocumentManagerBuilder {
    private lateinit var _managerType: ManagerType

    private lateinit var _localConfig: DocumentLocalConfig
    private lateinit var _awsConfig: DocumentAwsConfig

    fun local(config: DocumentLocalConfig): DocumentManagerBuilder {
        this._managerType = ManagerType.LOCAL
        this._localConfig = config
        return this
    }

    fun aws(config: DocumentAwsConfig): DocumentManagerBuilder {
        this._managerType = ManagerType.AWS
        this._awsConfig = config
        return this
    }

    fun dummy(): DocumentManagerBuilder {
        this._managerType = ManagerType.DUMMY
        return this
    }

    fun build(): DocumentManager {
        if (!::_managerType.isInitialized) throw IllegalStateException("DOCUMENT_MODULE_BUILD_FAILED")

        return when (_managerType) {
            ManagerType.DUMMY -> DummyDocumentManager()
            ManagerType.LOCAL -> LocalDocumentManager(_localConfig)
            ManagerType.AWS -> AwsDocumentManager(_awsConfig)
        }
    }

    private enum class ManagerType { DUMMY, LOCAL, AWS }
}
