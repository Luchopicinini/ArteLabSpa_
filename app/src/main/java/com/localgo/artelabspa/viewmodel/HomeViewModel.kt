package com.localgo.artelabspa.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.localgo.artelabspa.data.remote.dto.ProductoDto
import com.localgo.artelabspa.data.repository.ExternalProductRepository
import com.localgo.artelabspa.data.remote.ArtRetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    // Repositorio usando API de arte
    private val repo = ExternalProductRepository(ArtRetrofitClient.metApi)

    // Lista de productos y filtros
    private val _productos = MutableStateFlow<List<ProductoDto>>(emptyList())
    val productos: StateFlow<List<ProductoDto>> = _productos

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory

    private val _maxPrice = MutableStateFlow<Double?>(null)
    val maxPrice: StateFlow<Double?> = _maxPrice

    init {
        loadProductos()
    }

    fun loadProductos() {
        viewModelScope.launch {
            try {
                val lista = repo.getProductos() // trae cuadros
                _productos.value = lista
                Log.d("HOME_VM", "Productos cargados: ${lista.size}")
            } catch (e: Exception) {
                Log.e("HOME_VM", "Error cargando productos: $e")
            }
        }
    }

    // Filtrar por nombre
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // Filtrar por categoría
    fun setCategory(category: String?) {
        _selectedCategory.value = category
    }

    // Filtrar por precio máximo (opcional, arte no tiene precio real)
    fun setMaxPrice(price: Double?) {
        _maxPrice.value = price
    }

    // Lista filtrada según búsqueda, categoría y precio
    val filteredProducts: StateFlow<List<ProductoDto>> =
        MutableStateFlow<List<ProductoDto>>(emptyList()).also { flow ->
            viewModelScope.launch {
                _productos.collect { lista ->
                    val filtered = lista.filter { producto ->
                        val matchesName =
                            producto.title.contains(_searchQuery.value, ignoreCase = true)
                        val matchesCategory =
                            _selectedCategory.value?.let { producto.category == it } ?: true
                        val matchesPrice = _maxPrice.value?.let { producto.price <= it } ?: true
                        matchesName && matchesCategory && matchesPrice
                    }
                    flow.value = filtered
                }
            }
        }
}
