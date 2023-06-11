package de.soheilnazari.bfour.user.controller

import de.soheilnazari.bfour.user.persistence.UserEntity
import de.soheilnazari.bfour.user.persistence.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user", consumes = ["application/json"], produces = ["application/json"])
class UserController {

  @Autowired
  lateinit var userService: UserService

  @PostMapping
  fun saveUser(@RequestBody user: UserEntity): UserEntity {
    return userService.save(user)
  }

  @GetMapping("/{id}")
  fun getUserById(@PathVariable id: Long): UserEntity {
    return userService.getById(id).orElse(null)
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
}