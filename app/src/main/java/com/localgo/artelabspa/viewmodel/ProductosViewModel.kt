package com.localgo.artelabspa.viewmodel

import androidx.lifecycle.ViewModel
import com.localgo.artelabspa.data.repository.ProductosRepository
import com.localgo.artelabspa.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductosViewModel(
    private val repository: ProductosRepository = ProductosRepository()
) : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    init {
        cargarProductos()
    }

    private fun cargarProductos() {
        _productos.value = repository.getMockProductos()
    }
}
