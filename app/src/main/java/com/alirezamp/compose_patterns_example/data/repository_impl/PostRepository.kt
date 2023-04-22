package com.alirezamp.compose_patterns_example.data.repository_impl

import com.alirezamp.compose_patterns_example.data.datasource.IPostDatasource
import com.alirezamp.compose_patterns_example.domain.model.Post
import com.alirezamp.compose_patterns_example.domain.repository.IPostRepository
import com.alirezamp.compose_patterns_example.domain.utils.Resource
import com.alirezamp.compose_patterns_example.utils.ApiException
import com.alirezamp.compose_patterns_example.utils.OnlineChecker

class PostRepository(
    private val postRemoteDatasource: IPostDatasource,
    private val postLocalDatasource: IPostDatasource,
    private val onlineChecker: OnlineChecker,
) : IPostRepository {

    override suspend fun getPosts(): Resource<List<Post>> {
        return if (onlineChecker.isOnline()) getPostsFromRemoteDb() else getPostsFromLocalDb()
    }

    /*
    * get posts from remote db
    * if has error get from local db
    */
    private suspend fun getPostsFromRemoteDb(): Resource<List<Post>> {
        return try {
            val posts = postRemoteDatasource.getPosts()
            postLocalDatasource.savePosts(posts)
            return Resource.Success(posts)
        } catch (e: ApiException) {
            Resource.Error(
                message = "${e.message} : ${e.statusCode}",
                data = postLocalDatasource.getPosts(),
            )
        } catch (e: Exception) {
            Resource.Error(
                message = "${e.message}",
                data = postLocalDatasource.getPosts(),
            )
        }
    }

    /*
    * load posts from local db
    */
    private suspend fun getPostsFromLocalDb(): Resource<List<Post>> {
        return try {
            Resource.Success(postLocalDatasource.getPosts())
        } catch (e: Exception) {
            Resource.Error(
                message = "${e.message}",
                data = emptyList()
            )
        }
    }

}