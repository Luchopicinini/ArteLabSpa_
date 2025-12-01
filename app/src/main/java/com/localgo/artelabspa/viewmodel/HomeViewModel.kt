package com.localgo.artelabspa.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.localgo.artelabspa.data.repository.ExternalProductRepository
import com.localgo.artelabspa.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repo = ExternalProductRepository(RetrofitClient.api)

    private val _externalProductTitle = MutableStateFlow("Cargando...")
    val externalProductTitle: StateFlow<String> = _externalProductTitle

    // -----------------------------
    // Cargar un producto desde FakeStore API
    fun loadExternalProduct() {
        viewModelScope.launch {
            try {
                val producto = repo.getExternalProduct()
                _externalProductTitle.value = "${producto.title} - ${producto.price} USD"
            } catch (e: Exception) {
                _externalProductTitle.value = "Error cargando producto externo"
            }
        }
    }

    // -----------------------------
    // Probar conexión a Render (sin usar en UI)
    fun testRenderConnection() {
        viewModelScope.launch {
            try {
                val productos = RetrofitClient.renderApi.getProductos()
                Log.d("RENDER_TEST", "Conexión OK: ${productos.size} productos")
            } catch (e: Exception) {
                Log.e("RENDER_TEST", "Error conexión Render: $e")
            }
        }
    }
}
