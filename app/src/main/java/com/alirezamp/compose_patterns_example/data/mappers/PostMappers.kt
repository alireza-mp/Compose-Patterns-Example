package com.alirezamp.compose_patterns_example.data.mappers

import com.alirezamp.compose_patterns_example.data.local.model.PostLocalDto
import com.alirezamp.compose_patterns_example.data.remote.model.PostRemoteDto
import com.alirezamp.compose_patterns_example.domain.model.Post

/*
* map remote dto to domain model
*/
fun PostRemoteDto.mapToDomainModel(): Post {
    return Post(
        body = body,
        id = id,
        title = title,
        userId = userId,
    )
}

/*
* map local dto to domain model
*/
fun PostLocalDto.mapToDomainModel(): Post {
    return Post(
        body = body,
        id = pId,
        title = title,
        userId = userId,
    )
}

/*
* map domain model to local dto
*/

fun Post.mapFromDomainModel(): PostLocalDto {
    val post = PostLocalDto()
    post.apply {
        this.body = this@mapFromDomainModel.body
        this.pId = this@mapFromDomainModel.id
        this.title = this@mapFromDomainModel.title
        this.userId = this@mapFromDomainModel.userId
    }
    return post
}
