package com.example.blog.control

import com.example.blog.Article
import com.example.blog.ArticleRepository
import com.example.blog.User
import com.example.blog.UserRepository
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/article")
class ArticleController(private val repository: ArticleRepository) {

	@GetMapping("/")
	fun findAll() = repository.findAllByOrderByAddedAtDesc()

	@GetMapping("/{slug}")
	fun findOne(@PathVariable slug: String) =
			repository.findBySlug(slug) ?: throw ResponseStatusException(NOT_FOUND, "This article does not exist")

	@PostMapping("/")
	fun addArticle(@RequestBody article: Article) = repository.save(article)

	@DeleteMapping("/{id}")
	fun deleteArticleById(@PathVariable id: Long) {
		repository.deleteById(id)
	}

}

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
