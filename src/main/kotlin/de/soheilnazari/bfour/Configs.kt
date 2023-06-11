package de.soheilnazari.bfour

class Configs {
  //TO-DO
  val MAX_CHARS_TITLE: IntRange = IntRange(2, 20)
  val MAX_CHARS_DESC: IntRange = IntRange(0, 100)
  val ERR_MSG_TITLE_CHARS: String = "Title must have minimum of %s characters and cannot exceed %s characters".format(MAX_CHARS_TITLE.first, MAX_CHARS_TITLE.last)
  val ERR_MSG_DESC_CHARS: String = "Description must have minimum of %s characters and cannot exceed %s characters".format(MAX_CHARS_DESC.first, MAX_CHARS_DESC.last)

  //USER
  val MAX_CHARS_FIRSTNAME: IntRange = IntRange(2, 40)
  val MAX_CHARS_LASTNAME: IntRange = IntRange(2, 40)

  val ERR_MSG_FIRSTNAME_CHARS: String = "Firstname must have minimum of %s characters and cannot exceed %s characters".format(MAX_CHARS_FIRSTNAME.first, MAX_CHARS_FIRSTNAME.last)
  val ERR_MSG_LASTNAME_CHARS: String = "Lastname must have minimum of %s characters and cannot exceed %s characters".format(MAX_CHARS_LASTNAME.first, MAX_CHARS_LASTNAME.last)
}