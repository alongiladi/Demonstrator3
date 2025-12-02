package com.example.demonstrator3.Screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.* // חשוב מאוד בשביל remember ו-mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.demonstrator3.ShoppingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(
    viewModel: ShoppingViewModel,
    onNavigateBack: () -> Unit
) {
    // משתנים לשמירת הטקסט שהמשתמש מקליד כרגע
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Add New Item") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Item Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it }, // אפשר להוסיף סינון למספרים בלבד
                label = { Text("Quantity") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (name.isNotEmpty()) {
                        viewModel.addItem(name, quantity) // קריאה ל-ViewModel
                        onNavigateBack() // חזרה למסך הקודם
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Item")
            }
        }
    }
}