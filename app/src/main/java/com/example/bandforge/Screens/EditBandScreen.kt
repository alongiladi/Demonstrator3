package com.example.bandforge.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bandforge.BandViewModel
import com.example.bandforge.data.BandItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBandScreen(
    bandId: Int,
    viewModel: BandViewModel,
    onNavigateBack: () -> Unit
) {
    val band by viewModel.getBandById(bandId).collectAsState(initial = null)

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
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = genre,
            onValueChange = { genre = it },
            label = { Text("Genre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                band?.let {
                    val updatedBand = it.copy(name = name, genre = genre, notes = notes)
                    viewModel.updateItem(updatedBand)
                    onNavigateBack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Changes")
        }
    }
}