package com.survivalcoding.com.survivalcoding.day17.data_source

import com.survivalcoding.com.survivalcoding.day17.dto.MovieDto
import com.survivalcoding.com.survivalcoding.day17.dto.MovieResponse
import com.survivalcoding.day17.data_source.MovieDataSource
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

class MovieDataSourceImpl(
    private val client: HttpClient = HttpClient(CIO)
) : MovieDataSource {
    override suspend fun getUpcomingMovies(): List<MovieDto> {
        val jsonBody =
            client.get("https://api.themoviedb.org/3/movie/upcoming?api_key=a64533e7ece6c72731da47c9c8bc691f&language=ko-KR&page=1")
                .bodyAsText()
        val movieResult = Json.decodeFromString<MovieResponse>(jsonBody)

        if (movieResult.results == null) {
            return emptyList()
        }

        return movieResult.results
    }
}