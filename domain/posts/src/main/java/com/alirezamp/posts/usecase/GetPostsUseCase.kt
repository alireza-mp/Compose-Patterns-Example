package com.alirezamp.posts.usecase

import com.alirezamp.posts.repository.IPostRepository
import javax.inject.Inject

class GetPostsUseCase
@Inject
constructor(
    private val postRepository: IPostRepository,
) {
    operator fun invoke() = postRepository.getPosts()
}

