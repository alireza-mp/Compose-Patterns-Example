package com.alirezamp.compose_patterns_example.domain.usecase

import com.alirezamp.compose_patterns_example.domain.repository.IPostRepository

class GetPostsUseCase(
    private val postRepository: IPostRepository,
) {
    suspend operator fun invoke() = postRepository.getPosts()
}