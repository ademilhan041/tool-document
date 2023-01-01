package com.ailhan.tool.document.manager.aws

import com.ailhan.tool.document.Content
import com.ailhan.tool.document.ContentData
import com.ailhan.tool.document.DocumentManager
import com.ailhan.tool.document.MimeTypeMap
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.*
import org.slf4j.LoggerFactory
import java.net.URL

class AwsDocumentManager(private val config: DocumentAwsConfig) : DocumentManager {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val s3: AmazonS3 = AmazonS3ClientBuilder.standard()
        .withRegion(config.awsRegion)
        .withCredentials(
            AWSStaticCredentialsProvider(
                BasicAWSCredentials(
                    config.awsAccessKeyId,
                    config.awsSecretKeyId
                )
            )
        )
        .build()

    override fun list(): List<URL> {
        var resp: ObjectListing? = s3.listObjects(ListObjectsRequest().withBucketName(config.awsBucket))
        if (resp == null || resp.objectSummaries == null || resp.objectSummaries.isEmpty()) return listOf()

        val files = resp.objectSummaries.map { it.key }.toMutableList()
        while (resp!!.isTruncated) {
            resp = s3.listNextBatchOfObjects(resp)
            if (resp != null && resp.objectSummaries != null) files.addAll(resp.objectSummaries.map { it.key })
        }

        return files.map { URL(it) }
    }

    override fun upload(content: Content): URL {
        val request =
            PutObjectRequest(config.awsBucket, content.filename, content.data.stream, ObjectMetadata()).publicAccess()
        s3.putObject(request)
        content.data.close()
        logger.info("${content.filename} has been uploaded to AWS Bucket[${config.awsBucket}].")
        return s3.getUrl(config.awsBucket, content.filename)
    }

    override fun download(filename: String): Content {
        val stream = s3.getObject(GetObjectRequest(config.awsBucket, filename)).objectContent
        val type = MimeTypeMap.mimeTypeFromUrl(filename)
        return Content(filename, type, ContentData.stream(stream))
    }

    override fun delete(filename: String): Boolean {
        s3.deleteObject(DeleteObjectRequest(config.awsBucket, filename))
        logger.info("$filename has been deleted from AWS Bucket[${config.awsBucket}].")
        return true
    }
}

fun PutObjectRequest.publicAccess(): PutObjectRequest {
    val acl = AccessControlList()
    acl.grantPermission(GroupGrantee.AllUsers, Permission.Read)
    accessControlList = acl
    return this
}
