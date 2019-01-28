package io.realworld.user.query

import io.realworld.shared.refs.UserId
import io.realworld.user.domain.UserReadRepository
import org.springframework.stereotype.Service

@Service
class UserQueryService(
        private val userReadRepository: UserReadRepository
) {
    fun findBy(userId: UserId) = userReadRepository.getBy(userId)
}
