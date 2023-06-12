package de.soheilnazari.bfour.todo.persistence

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.time.LocalDateTime

@Document("todos")
data class TodoDocument(
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    var id: String?,
    var title: String,
    var description: String,
    var userId: Long,
    var priority: Int = 2, // 1 = High, 2 = Medium, 3 = Low
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    var dueDate: LocalDateTime,
    var done: Boolean = false
)
