package com.alirezamp.compose_patterns_example.data.datasource_impl.post

import com.alirezamp.compose_patterns_example.data.datasource.IPostDatasource
import com.alirezamp.compose_patterns_example.data.local.model.PostLocalDto
import com.alirezamp.compose_patterns_example.data.mappers.mapFromDomainModel
import com.alirezamp.compose_patterns_example.data.mappers.mapToDomainModel
import com.alirezamp.compose_patterns_example.domain.model.Post
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query

class PostLocalDatasource(
    private val realm: Realm,
) : IPostDatasource {

    override suspend fun getPosts(): List<Post> {
        return try {
            realm.copyFromRealm(realm.query<PostLocalDto>().find()).map { it.mapToDomainModel() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun savePosts(posts: List<Post>) {
        try {
            val list = posts.map { it.mapFromDomainModel() }
            list.map { realm.writeBlocking { copyToRealm(it, updatePolicy = UpdatePolicy.ALL) } }
        } catch (e: Exception) {
            throw e
        }
    }

}
