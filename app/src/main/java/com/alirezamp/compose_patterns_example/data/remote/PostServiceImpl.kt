package com.alirezamp.compose_patterns_example.data.remote

import com.alirezamp.compose_patterns_example.data.remote.model.PostRemoteDto
import com.alirezamp.compose_patterns_example.utils.ApiException
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class PostServiceImpl(
    private val client: HttpClient,
) : PostService {

    override suspend fun getPosts(): List<PostRemoteDto> {
        return try {
            client.get { url(HttpRoutes.POSTS) }
        } catch (e: RedirectResponseException) {
            throw ApiException(
                message = e.response.status.description,
                statusCode = e.response.status.value,
            )
        } catch (e: ClientRequestException) {
            throw ApiException(
                message = e.response.status.description,
                statusCode = e.response.status.value,
            )
        } catch (e: ServerResponseException) {
            throw ApiException(
                message = e.response.status.description,
                statusCode = e.response.status.value,
            )
        } catch (e: Exception) {
            throw ApiException(
                message = "check your internet connection!",
            )
        }
    }

}