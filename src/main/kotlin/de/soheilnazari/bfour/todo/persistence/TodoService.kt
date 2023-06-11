package de.soheilnazari.bfour.todo.persistence

import de.soheilnazari.bfour.CustomError
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
    if (userService.getById(todo.userId).isEmpty) {
      return Pair(
          TodoDocument("", "", "", 0),
          CustomError("Error", "User not found")
      )
    }
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
}