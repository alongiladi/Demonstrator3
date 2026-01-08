package com.example.demonstrator3.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.demonstrator3.BandViewModel
import com.example.demonstrator3.data.BandItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BandListScreen(
    viewModel: BandViewModel,
    onNavigateToAdd: () -> Unit
) {
    val items by viewModel.allItems.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("BandForge - Saved Names") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Default.Add, contentDescription = "Add Band Name")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items) { item ->
                BandItemRow(item = item, onDelete = { viewModel.deleteItem(item) })
            }
        }
    }
}

@Composable
fun BandItemRow(item: BandItem, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Genre: ${item.genre}", style = MaterialTheme.typography.bodyMedium)
                if (item.notes.isNotEmpty()) {
                    Text(text = "Notes: ${item.notes}", style = MaterialTheme.typography.bodySmall)
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}