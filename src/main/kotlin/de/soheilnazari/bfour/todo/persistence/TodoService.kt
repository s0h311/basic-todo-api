package de.soheilnazari.bfour.todo.persistence

import de.soheilnazari.bfour.Configs
import de.soheilnazari.bfour.CustomError
import de.soheilnazari.bfour.user.persistence.UserEntity
import de.soheilnazari.bfour.user.persistence.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoService {

  @Autowired
  lateinit var todoRepository: TodoRepository

  @Autowired
  lateinit var userService: UserService

  fun save(todo: TodoDocument): Pair<TodoDocument, CustomError> {
    if (todo.title.length !in Configs().MAX_CHARS_TITLE) return Pair(
        TodoDocument("", "", "", 0),
        CustomError("Error", Configs().ERR_MSG_TITLE_CHARS)
    )
    if (todo.description.length !in Configs().MAX_CHARS_DESC) return Pair(
        TodoDocument("", "", "", 0),
        CustomError("Error", Configs().ERR_MSG_DESC_CHARS)
    )
    if (userService.getById(todo.userId).first.id == 0L) return Pair(
        TodoDocument("", "", "", 0),
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

  fun getAll(): List<TodoDocument> {
    return todoRepository.findAll()
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
        todoRepository.getTodoDocumentsByUserId(id),
        CustomError("", "")
    )
  }
}