package com.example.blog.controller

import com.example.blog.entity.User
import com.example.blog.repository.UserRepository
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/user")
class UserController(private val repository: UserRepository) {

	@GetMapping("/")
	fun findAll() = repository.findAll()

	@GetMapping("/{login}")
	fun findOne(@PathVariable login: String) = repository.findByLogin(login) ?: throw ResponseStatusException(NOT_FOUND, "This user does not exist")

	@PostMapping("/")
	fun addUser(@RequestBody user: User) = repository.save(user)

	@DeleteMapping("/{id}")
	fun deleteUserById(@PathVariable id: Long) = repository.deleteById(id)
}
