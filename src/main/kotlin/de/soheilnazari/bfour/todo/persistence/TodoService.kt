package de.soheilnazari.bfour.todo.persistence

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoService {

  @Autowired
  lateinit var todoRepository: TodoRepository

  fun save(todo: TodoDocument): TodoDocument {
    return todoRepository.save(todo)
  }

  fun getAll(): List<TodoDocument> {
    return todoRepository.findAll()
  }
}