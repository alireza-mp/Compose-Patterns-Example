package com.alirezamp.posts_remote.mapper

import com.alirezamp.posts.model.Post
import com.alirezamp.posts_remote.dto.PostRemoteDto

fun PostRemoteDto.toPost(): Post = Post(
    id = this.id,
    userId = this.userId,
    body = this.body,
    title = this.title,
)

fun Post.toPostRemoteDto(): PostRemoteDto = PostRemoteDto(
    id = this.id,
    userId = this.userId,
    body = this.body,
    title = this.title,
)