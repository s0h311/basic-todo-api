package de.soheilnazari.bfour.todo.controller

import de.soheilnazari.bfour.CustomError
import de.soheilnazari.bfour.todo.persistence.TodoDocument
import de.soheilnazari.bfour.todo.persistence.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/todo", consumes = ["application/json"], produces = ["application/json"])
class TodoController {

  @Autowired
  lateinit var todoService: TodoService

  @PostMapping
  fun saveTodo(@RequestBody todo: TodoDocument): Pair<TodoDocument, CustomError> {
    return todoService.save(todo)
  }

  @GetMapping("/{id}")
  fun getTodoById(@PathVariable id: String): Pair<TodoDocument, CustomError> {
    val result: Optional<TodoDocument> = todoService.getById(id)
    if (result.isEmpty) {
      return Pair(
          TodoDocument("", "", "", 0),
          CustomError("Error", "Todo not found")
      )
    }
    return Pair(
        result.get(),
        CustomError("", "")
    )
  }

  @GetMapping
  fun getAllTodos(): List<TodoDocument> {
    return todoService.getAll()
  }

  @DeleteMapping("/{id}")
  fun removeTodoById(@PathVariable id: String) {
    todoService.removeById(id)
  }

  @DeleteMapping
  fun removeAllTodos() {
    todoService.removeAll()
  }
}