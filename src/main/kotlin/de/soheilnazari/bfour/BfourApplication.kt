package de.soheilnazari.bfour

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BfourApplication

fun main(args: Array<String>) {
	runApplication<BfourApplication>(*args)
}
