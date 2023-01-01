package com.ailhan.tool.document.manager.aws

import java.util.*

/**
 * Available Properties
 *
 * doc.aws.bucket=<bucket>
 * doc.aws.region=<region>
 * doc.aws.accessKeyId=<username>
 * doc.aws.secretKeyId=<secretKeyId>
 */
class DocumentAwsConfig(props: Properties) {
    var awsBucket: String = props.getProperty("doc.aws.bucket")
    var awsRegion: String = props.getProperty("doc.aws.region")
    var awsAccessKeyId: String = props.getProperty("doc.aws.accessKeyId")
    var awsSecretKeyId: String = props.getProperty("doc.aws.secretKeyId")

    init {
        if (awsBucket.isEmpty()) throw IllegalArgumentException("DOC_AWS_BUCKET_MISSING")
        if (awsRegion.isEmpty()) throw IllegalArgumentException("DOC_AWS_REGION_MISSING")
        if (awsAccessKeyId.isEmpty()) throw IllegalArgumentException("DOC_AWS_ACCESS_KEY_MISSING")
        if (awsSecretKeyId.isEmpty()) throw IllegalArgumentException("DOC_AWS_SECRET_KEY_MISSING")
    }
}
