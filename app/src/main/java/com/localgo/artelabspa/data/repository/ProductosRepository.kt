package com.localgo.artelabspa.data.repository

import com.localgo.artelabspa.model.Producto

class ProductosRepository {

    fun getMockProductos(): List<Producto> {
        return listOf(
            Producto(
                id = "1",
                nombre = "Cuadro Acrílico Azul",
                descripcion = "Obra en acrílico 60x80 cm",
                precio = 45000,
                imagen = "https://placehold.co/600x400"
            ),
            Producto(
                id = "2",
                nombre = "Escultura Minimalista",
                descripcion = "Escultura moderna de 30cm",
                precio = 55000,
                imagen = "https://placehold.co/600x400"
            )
        )
    }
}
