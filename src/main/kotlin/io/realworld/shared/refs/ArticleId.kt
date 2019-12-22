package io.realworld.shared.refs

import io.realworld.shared.infrastructure.IdNotPersistedDelegate
import io.realworld.shared.infrastructure.RefId
import javax.persistence.AttributeConverter

sealed class ArticleId : RefId<Long>() {
    object New : ArticleId() {
        override val value: Long by IdNotPersistedDelegate<Long>()
    }

    data class Persisted(override val value: Long) : ArticleId() {
        override fun toString() = "ArticleId(value=$value)"
    }
}

class ArticleIdConverter : AttributeConverter<ArticleId, Long> {
    override fun convertToDatabaseColumn(attribute: ArticleId): Long {
        return attribute.value
    }

    override fun convertToEntityAttribute(dbData: Long): ArticleId {
        return ArticleId.Persisted(dbData)
    }
}
