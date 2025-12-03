package com.localgo.artelabspa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.localgo.artelabspa.viewmodel.ProductosViewModel

@Composable
fun ProductosScreen() {

    val viewModel = remember { ProductosViewModel() }
    val productos by viewModel.productos.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Productos",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        productos.forEach { producto ->

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Column {

                    Image(
                        painter = rememberAsyncImagePainter(producto.imagen),
                        contentDescription = producto.nombre,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )

                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(producto.nombre, fontWeight = FontWeight.Bold)
                        Text(producto.descripcion)
                        Text("${producto.precio} CLP", fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
