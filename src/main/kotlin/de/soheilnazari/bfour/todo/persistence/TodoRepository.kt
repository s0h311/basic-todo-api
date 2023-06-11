package de.soheilnazari.bfour.todo.persistence

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : MongoRepository<TodoDocument, String> {

  fun getTodoDocumentsByUserId(id: Long): List<TodoDocument>
}