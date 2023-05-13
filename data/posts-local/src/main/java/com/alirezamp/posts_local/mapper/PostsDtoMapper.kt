package com.alirezamp.posts_local.mapper

import com.alirezamp.posts.model.Post
import com.alirezamp.posts_local.dto.PostLocalDto
import kotlin.random.Random

fun Post.toPostLocalDto(): PostLocalDto = PostLocalDto(
    id = Random.nextInt(),
    title = this.title,
    body = this.body,
    userId = this.userId,
)


fun PostLocalDto.toPost(): Post = Post(
    id = this.id,
    title = this.title,
    body = this.body,
    userId = this.userId,
)
