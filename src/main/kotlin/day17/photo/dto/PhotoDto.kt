package org.hyunjung.day17.photo.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoDto(
    val id: Int,
    val type: String? = null,
    val title: String? = null,
    val content: String? = null,
    val url: String? = null,
    val caption: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)