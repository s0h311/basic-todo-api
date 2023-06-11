package de.soheilnazari.bfour.todo.persistence

import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

@Document("todos")
data class TodoDocument(
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    var id: String?,
    var title: String,
    var description: String,
    var userId: Long
)
