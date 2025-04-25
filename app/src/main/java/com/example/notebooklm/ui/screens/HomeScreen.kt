package com.example.notebooklm.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notebooklm.data.api.DocumentResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var documents by remember { mutableStateOf<List<DocumentResponse>>(emptyList()) }
    var selectedDocument by remember { mutableStateOf<DocumentResponse?>(null) }
    var chatMessage by remember { mutableStateOf("") }
    var chatHistory by remember { mutableStateOf<List<String>>(emptyList()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NotebookLM") },
                actions = {
                    IconButton(onClick = { /* TODO: Implement new document creation */ }) {
                        Icon(Icons.Default.Add, contentDescription = "New Document")
                    }
                }
            )
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Document List
            Card(
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(documents) { document ->
                        ListItem(
                            headlineContent = { Text(document.title) },
                            supportingContent = { Text(document.status) },
                            modifier = Modifier.clickable { selectedDocument = document }
                        )
                    }
                }
            }

            // Chat Area
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                // Chat History
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    items(chatHistory) { message ->
                        Text(
                            text = message,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                // Chat Input
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = chatMessage,
                        onValueChange = { chatMessage = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Type your message...") }
                    )
                    IconButton(
                        onClick = {
                            if (chatMessage.isNotBlank()) {
                                chatHistory = chatHistory + chatMessage
                                chatMessage = ""
                            }
                        }
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Send")
                    }
                }
            }
        }
    }
} 