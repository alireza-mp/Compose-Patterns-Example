package com.alirezamp.compose_patterns_example.domain.usecase

import com.alirezamp.compose_patterns_example.domain.repository.IPostRepository
import javax.inject.Inject

class GetPostsUseCase
@Inject
constructor(
    private val postRepository: IPostRepository,
) {
    suspend operator fun invoke() = postRepository.getPosts()
}