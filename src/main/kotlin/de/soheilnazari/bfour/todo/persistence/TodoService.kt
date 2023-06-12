package de.soheilnazari.bfour.todo.persistence

import de.soheilnazari.bfour.Configs
import de.soheilnazari.bfour.CustomError
import de.soheilnazari.bfour.user.persistence.UserEntity
import de.soheilnazari.bfour.user.persistence.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class TodoService {

  @Autowired
  lateinit var todoRepository: TodoRepository

  @Autowired
  lateinit var userService: UserService

  fun save(todo: TodoDocument): Pair<TodoDocument, CustomError> {
    if (todo.title.length !in Configs().MAX_CHARS_TITLE) return Pair(
        TodoDocument("", "", "", 0L, dueDate = LocalDateTime.MIN),
        CustomError("Error", Configs().ERR_MSG_TITLE_CHARS)
    )
    if (todo.description.length !in Configs().MAX_CHARS_DESC) return Pair(
        TodoDocument("", "", "", 0L, dueDate = LocalDateTime.MIN),
        CustomError("Error", Configs().ERR_MSG_DESC_CHARS)
    )
    if (userService.getById(todo.userId).first.id == 0L) return Pair(
        TodoDocument("", "", "", 0L, dueDate = LocalDateTime.MIN),
        CustomError("Error", "User not found")
    )
    return Pair(
        todoRepository.save(todo),
        CustomError("", "")
    )
  }

  fun getById(id: String): Optional<TodoDocument> {
    return todoRepository.findById(id)
  }

  fun getAll(
      dueDate: LocalDateTime?,
      dueDateBefore: LocalDateTime?,
      priority: Int?,
      done: Boolean?
  ): Pair<List<TodoDocument>, CustomError> {
    var result: List<TodoDocument> = todoRepository.findAll()

    if (dueDate != null && dueDateBefore != null) return Pair(
        emptyList(),
        CustomError("Error", "dueDate and dueDateBefore cannot be present simultaneously")
    )
    if (dueDate != null) result = result.filter { todo -> todo.dueDate == dueDate }
    else if (dueDateBefore != null) result = result.filter { todo -> todo.dueDate.isBefore(dueDateBefore) }
    if (priority != null) result = result.filter { todo -> todo.priority == priority }
    if (done != null) result = result.filter { todo -> todo.done == done }

    return Pair(
        result,
        CustomError("", "")
    )
  }

  fun removeById(id: String) {
    todoRepository.deleteById(id)
  }

  fun removeAll() {
    todoRepository.deleteAll()
  }

  fun getTodosByUserId(id: Long): Pair<List<TodoDocument>, CustomError> {
    val user: Pair<UserEntity, CustomError> = userService.getById(id)

    if (user.first.id == 0L) return Pair(
        emptyList(),
        CustomError("Error", "User not found")
    )
    return Pair(
        todoRepository.findAllByUserId(id),
        CustomError("", "")
    )
  }
}