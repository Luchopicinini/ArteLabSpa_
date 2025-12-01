package com.localgo.artelabspa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.localgo.artelabspa.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit // <-- cerrar sesión
) {
    val productos by viewModel.filteredProducts.collectAsState(emptyList())
    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Botones perfil y cerrar sesión en fila
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onProfileClick) {
                Text("Perfil")
            }

            Button(onClick = onLogoutClick) {
                Text("Cerrar sesión")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Buscar por nombre
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.setSearchQuery(it)
            },
            label = { Text("Buscar cuadro") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        // Filtrar por categoría
        OutlinedTextField(
            value = selectedCategory ?: "",
            onValueChange = {
                selectedCategory = if (it.isEmpty()) null else it
                viewModel.setCategory(selectedCategory)
            },
            label = { Text("Filtrar por categoría") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        //  Grid de cuadros de arte
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(productos) { producto ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = producto.image,
                            contentDescription = producto.title,
                            modifier = Modifier
                                .size(120.dp)
                                .fillMaxWidth(),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(producto.title, style = MaterialTheme.typography.bodyLarge)
                        Text(producto.category, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
