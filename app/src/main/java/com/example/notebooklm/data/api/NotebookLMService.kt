package com.example.notebooklm.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Header

interface NotebookLMService {
    @POST("documents")
    suspend fun createDocument(
        @Header("Authorization") authToken: String,
        @Body request: CreateDocumentRequest
    ): DocumentResponse

    @POST("documents/{documentId}/chat")
    suspend fun chatWithDocument(
        @Header("Authorization") authToken: String,
        @Body request: ChatRequest
    ): ChatResponse
}

data class CreateDocumentRequest(
    val title: String,
    val content: String,
    val mimeType: String = "text/plain"
)

data class DocumentResponse(
    val id: String,
    val title: String,
    val status: String
)

data class ChatRequest(
    val message: String,
    val documentId: String
)

data class ChatResponse(
    val response: String,
    val sources: List<Source>
)

data class Source(
    val pageNumber: Int,
    val content: String
) 