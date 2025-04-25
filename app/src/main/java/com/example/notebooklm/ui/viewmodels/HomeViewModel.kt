package com.example.notebooklm.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebooklm.data.api.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val notebookLMService: NotebookLMService
) : ViewModel() {
    private val _documents = MutableStateFlow<List<DocumentResponse>>(emptyList())
    val documents: StateFlow<List<DocumentResponse>> = _documents.asStateFlow()

    private val _chatHistory = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatHistory: StateFlow<List<ChatMessage>> = _chatHistory.asStateFlow()

    private val _selectedDocument = MutableStateFlow<DocumentResponse?>(null)
    val selectedDocument: StateFlow<DocumentResponse?> = _selectedDocument.asStateFlow()

    fun createDocument(title: String, content: String, authToken: String) {
        viewModelScope.launch {
            try {
                val request = CreateDocumentRequest(title, content)
                val response = notebookLMService.createDocument(authToken, request)
                _documents.value = _documents.value + response
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun sendMessage(message: String, authToken: String) {
        viewModelScope.launch {
            try {
                val documentId = _selectedDocument.value?.id ?: return@launch
                val request = ChatRequest(message, documentId)
                val response = notebookLMService.chatWithDocument(authToken, request)
                
                _chatHistory.value = _chatHistory.value + ChatMessage(
                    text = message,
                    isUser = true
                ) + ChatMessage(
                    text = response.response,
                    isUser = false,
                    sources = response.sources
                )
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun selectDocument(document: DocumentResponse) {
        _selectedDocument.value = document
        _chatHistory.value = emptyList()
    }
}

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val sources: List<Source> = emptyList()
) 