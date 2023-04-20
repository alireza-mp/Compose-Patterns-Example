package com.alirezamp.compose_patterns_example.data.mappers

import com.alirezamp.compose_patterns_example.data.api.model.PostRemoteDto
import com.alirezamp.compose_patterns_example.data.db.entity.PostLocalDto
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
        id = id,
        title = title,
        userId = userId,
    )
}

/*
* map domain model to local dto
*/
fun Post.mapFromDomainModel(): PostLocalDto {
    return PostLocalDto(
        body = body,
        id = id,
        title = title,
        userId = userId,
    )
}
