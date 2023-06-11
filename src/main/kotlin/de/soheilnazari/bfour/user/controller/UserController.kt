package de.soheilnazari.bfour.user.controller

import de.soheilnazari.bfour.user.persistence.UserEntity
import de.soheilnazari.bfour.user.persistence.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

  @Autowired
  lateinit var userService: UserService

  @PostMapping("/user", produces = ["application/json"])
  @ResponseBody
  fun saveUser(@RequestBody user: UserEntity): UserEntity {
    return userService.save(user)
  }

  @GetMapping("/user", produces = ["application/json"])
  @ResponseBody
  fun getAllUsers(): List<UserEntity> {
    return userService.getAll()
  }
}