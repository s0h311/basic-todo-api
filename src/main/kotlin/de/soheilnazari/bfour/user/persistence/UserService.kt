package de.soheilnazari.bfour.user.persistence

import de.soheilnazari.bfour.Configs
import de.soheilnazari.bfour.CustomError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {

  @Autowired
  lateinit var userRepository: UserRepository

  fun save(user: UserEntity): Pair<UserEntity, CustomError> {
    if (user.firstName.length !in Configs().MAX_CHARS_FIRSTNAME) return Pair(
        UserEntity(0L, "", "", "", ""),
        CustomError("Error", Configs().ERR_MSG_FIRSTNAME_CHARS)
    )
    if (user.lastName.length !in Configs().MAX_CHARS_LASTNAME) return Pair(
        UserEntity(0L, "", "", "", ""),
        CustomError("Error", Configs().ERR_MSG_LASTNAME_CHARS)
    )
    return Pair(
        userRepository.save(user),
        CustomError("", "")
    )
  }

  fun getById(id: Long): Pair<UserEntity, CustomError> {
    val result: Optional<UserEntity> = userRepository.findById(id)
    if (result.isEmpty) {
      return Pair(
          UserEntity(0, "", "", "", ""),
          CustomError("Error", "User not found")
      )
    }
    return Pair(
        result.get(),
        CustomError("", "")
    )
  }

  fun getAll(): List<UserEntity> {
    return userRepository.findAll()
  }

  fun removeById(id: Long) {
    userRepository.deleteById(id)
  }

  fun removeAll() {
    userRepository.deleteAll()
  }
}