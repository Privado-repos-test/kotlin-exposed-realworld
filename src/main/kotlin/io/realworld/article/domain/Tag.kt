package io.realworld.article.domain

import io.realworld.shared.infrastructure.IdNotPersistedDelegate
import io.realworld.shared.infrastructure.RefId
import javax.persistence.AttributeConverter
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

sealed class TagId : RefId<Long>() {
    object New : TagId() {
        override val value: Long by IdNotPersistedDelegate<Long>()
    }

    data class Persisted(override val value: Long) : TagId() {
        override fun toString() = "TagId(value=$value)"
    }
}

@Entity
@Table(name = "tags")
data class Tag(
        @Id @GeneratedValue
        @Convert(converter = TagIdConverter::class)
        val id: TagId = TagId.New,
        val name: String
)

class TagIdConverter : AttributeConverter<TagId, Long> {
    override fun convertToDatabaseColumn(attribute: TagId): Long {
        return attribute.value
    }

    override fun convertToEntityAttribute(dbData: Long): TagId {
        return TagId.Persisted(dbData)
    }
}
