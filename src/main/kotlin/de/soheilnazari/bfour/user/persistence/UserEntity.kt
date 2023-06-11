package de.soheilnazari.bfour.user.persistence

import jakarta.persistence.*

@Entity
@Table(name = "users", schema = "bfour")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long?,
    var firstName: String,
    var lastName: String,
    @Column(unique = true)
    var email: String,
    var password: String
)
