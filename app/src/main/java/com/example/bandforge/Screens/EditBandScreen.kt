package com.example.bandforge.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bandforge.BandViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBandScreen(
    bandId: Int,
    viewModel: BandViewModel,
    onNavigateBack: () -> Unit
) {
    val band by viewModel.getBandById(bandId).collectAsState(initial = null)
    val isLoading by viewModel.isLoading.collectAsState()
    val generatedName by viewModel.generatedBandName.collectAsState()

    var name by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    LaunchedEffect(band) {
        band?.let {
            name = it.name
            genre = it.genre
            notes = it.notes
        }
    }

    LaunchedEffect(generatedName) {
        if (generatedName.isNotEmpty()) {
            name = generatedName
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Band Name") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )

        OutlinedTextField(
            value = genre,
            onValueChange = { genre = it },
            label = { Text("Genre") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )

        Button(
            onClick = { viewModel.generateBandName(genre, notes) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Generate with AI - Coming soon in CCL3!")
            }
        }

        Button(
            onClick = {
                band?.let {
                    val updatedBand = it.copy(name = name, genre = genre, notes = notes)
                    viewModel.updateItem(updatedBand)
                    onNavigateBack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text("Save Changes")
        }
    }
}