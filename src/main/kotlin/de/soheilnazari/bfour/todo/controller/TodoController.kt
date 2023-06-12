package de.soheilnazari.bfour.todo.controller

import de.soheilnazari.bfour.CustomError
import de.soheilnazari.bfour.todo.persistence.TodoDocument
import de.soheilnazari.bfour.todo.persistence.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
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
          TodoDocument("", "", "", 0L, dueDate = LocalDateTime.MIN),
          CustomError("Error", "Todo not found")
      )
    }
    return Pair(
        result.get(),
        CustomError("", "")
    )
  }

  @GetMapping
  fun getAllTodos(
      @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") @RequestParam dueDate: LocalDateTime?,
      @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") @RequestParam dueDateBefore: LocalDateTime?,
      @RequestParam priority: Int?,
      @RequestParam done: Boolean?
  ): Pair<List<TodoDocument>, CustomError> {
    return todoService.getAll(dueDate, dueDateBefore, priority, done)
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