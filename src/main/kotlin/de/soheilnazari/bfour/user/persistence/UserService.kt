package de.soheilnazari.bfour.user.persistence

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

  @Autowired
  lateinit var userRepository: UserRepository

  fun save(user: UserEntity): UserEntity {
    return userRepository.save(user)
  }

  fun removeById(id: Long) {
    userRepository.deleteById(id)
  }

  fun getAll(): List<UserEntity> {
    return userRepository.findAll()
  }
}