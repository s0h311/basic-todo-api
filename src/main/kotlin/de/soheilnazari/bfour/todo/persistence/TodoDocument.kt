package de.soheilnazari.bfour.todo.persistence

import jakarta.persistence.Id
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document("todos")
data class TodoDocument(
    @Id
    var id: ObjectId?,
    var title: String,
    var description: String,
    var userId: Long
)
