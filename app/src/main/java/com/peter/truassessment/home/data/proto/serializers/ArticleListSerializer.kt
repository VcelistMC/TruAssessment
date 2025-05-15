package com.peter.truassessment.home.data.proto.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.peter.truassessment.proto.ArticleList
import java.io.InputStream
import java.io.OutputStream

object ArticleListSerializer: Serializer<ArticleList> {
    override val defaultValue: ArticleList
        get() = ArticleList.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ArticleList {
        return try {
            ArticleList.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException){
            throw CorruptionException("Cannot read data from proto, ", exception)
        }
    }

    override suspend fun writeTo(t: ArticleList, output: OutputStream) {
        t.writeTo(output)
    }
}