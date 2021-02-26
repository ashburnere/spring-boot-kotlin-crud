package com.example.blog.controller

import com.example.blog.entity.Article
import com.example.blog.repository.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/article")
class ArticleController(private val repository: ArticleRepository) {

	@GetMapping("/")
	fun findAll() = repository.findAllByOrderByAddedAtDesc()

	@GetMapping("/{slug}")
	fun findOne(@PathVariable slug: String) =
			repository.findBySlug(slug) ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "This article does not exist"
            )

	@PostMapping("/")
	fun addArticle(@RequestBody article: Article) = repository.save(article)

	@DeleteMapping("/{id}")
	fun deleteArticleById(@PathVariable id: Long) {
		repository.deleteById(id)
	}

}