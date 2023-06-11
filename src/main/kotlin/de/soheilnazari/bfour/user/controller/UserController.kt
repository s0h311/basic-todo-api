package de.soheilnazari.bfour.user.controller

import de.soheilnazari.bfour.CustomError
import de.soheilnazari.bfour.todo.persistence.TodoDocument
import de.soheilnazari.bfour.todo.persistence.TodoService
import de.soheilnazari.bfour.user.persistence.UserEntity
import de.soheilnazari.bfour.user.persistence.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user", consumes = ["application/json"], produces = ["application/json"])
class UserController {

  @Autowired
  lateinit var userService: UserService

  @Autowired
  lateinit var todoService: TodoService

  @PostMapping
  fun saveUser(@RequestBody user: UserEntity): Pair<UserEntity, CustomError> {
    return userService.save(user)
  }

  @GetMapping("/{id}")
  fun getUserById(@PathVariable id: Long): Pair<UserEntity, CustomError> {
    return userService.getById(id)
  }

  @GetMapping
  fun getAllUsers(): List<UserEntity> {
    return userService.getAll()
  }

  @DeleteMapping("/{id}")
  fun removeUserById(@PathVariable id: Long) {
    userService.removeById(id)
  }

  @DeleteMapping
  fun removeUserById() {
    userService.removeAll()
  }

  @GetMapping("/{id}/todo")
  fun getTodosByUserId(@PathVariable id: Long): Pair<List<TodoDocument>, CustomError> {
    return todoService.getTodosByUserId(id)
  }
}