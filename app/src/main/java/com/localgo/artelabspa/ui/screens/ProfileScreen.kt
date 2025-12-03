package com.localgo.artelabspa.ui.screens

import android.app.Application
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.localgo.artelabspa.data.local.SessionManager
import com.localgo.artelabspa.data.remote.api.AvatarApiService
import com.localgo.artelabspa.data.repository.AvatarRepositoryBackend
import com.localgo.artelabspa.viewmodel.ProfileViewModel
import com.localgo.artelabspa.viewmodel.ProfileViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {}
) {


    val context = LocalContext.current

    // Crear instancia del ViewModel con Factory
    val viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            application = context.applicationContext as Application,
            avatarRepository = AvatarRepositoryBackend(
                api = AvatarApiService.create(),
                sessionManager = SessionManager(context),
                context = context
            ),
            sessionManager = SessionManager(context)
        )
    )

    val uiState by viewModel.uiState.collectAsState()

    // Picker de imágenes
    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                viewModel.uploadAvatar(uri)
            }
        }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mi Perfil") })
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // Avatar
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .clickable { imagePickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {

                if (uiState.isUploading) {
                    CircularProgressIndicator()
                } else {
                    AsyncImage(
                        model = uiState.avatarUrl,
                        contentDescription = "Avatar",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = uiState.userName,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = uiState.userEmail,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedButton(
                onClick = { imagePickerLauncher.launch("image/*") }
            ) {
                Text("Cambiar avatar")
            }

            if (uiState.error != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = uiState.error ?: "",
                    color = Color.Red
                )
            }
        }
    }
}
