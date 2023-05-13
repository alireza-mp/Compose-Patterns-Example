package com.alirezamp.posts.usecase

import com.alirezamp.posts.repository.IPostRepository
import javax.inject.Inject

class UpdatePostsUseCase
@Inject
constructor(
    private val postRepository: IPostRepository,
) {
    suspend operator fun invoke() = postRepository.updatePosts()
}