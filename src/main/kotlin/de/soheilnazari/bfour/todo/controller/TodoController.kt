package de.soheilnazari.bfour.todo.controller

import de.soheilnazari.bfour.todo.persistence.TodoDocument
import de.soheilnazari.bfour.todo.persistence.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TodoController {

  @Autowired
  lateinit var todoService: TodoService

  @PostMapping("/todo", produces = ["application/json"])
  fun saveTodo(@RequestBody todo: TodoDocument): TodoDocument {
    return todoService.save(todo)
  }

  @GetMapping("/todo", produces = ["application/json"])
  fun getAllTodos(): List<TodoDocument> {
    return todoService.getAll()
  }
}