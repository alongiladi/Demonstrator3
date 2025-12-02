package com.example.demonstrator3.Screens

// ייבואים של Compose UI
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.* import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// ייבואים מהפרויקט שלך (ViewModel ו-Data Class)
import com.example.demonstrator3.ShoppingViewModel
import com.example.demonstrator3.data.ShoppingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    viewModel: ShoppingViewModel,
    onNavigateToAdd: () -> Unit
) {
    // הופכים את ה-Flow של ה-ViewModel למשהו ש-Compose יודע לקרוא (State)
    // ברגע שהרשימה ב-DB תשתנה, המשתנה הזה יתעדכן והמסך יצויר מחדש
    val items by viewModel.allItems.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Shopping List") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
            }
        }
    ) { paddingValues ->
        // רשימה נגללת
        LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items) { item ->
                ShoppingItemRow(item = item, onDelete = { viewModel.deleteItem(item) })
            }
        }
    }
}

// רכיב שמצייר שורה אחת ברשימה
@Composable
fun ShoppingItemRow(item: ShoppingItem, onDelete: () -> Unit) {
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
                Text(text = "Quantity: ${item.quantity}", style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}