package com.localgo.artelabspa.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.localgo.artelabspa.data.local.SessionManager
import com.localgo.artelabspa.data.remote.api.AvatarApiService
import com.localgo.artelabspa.data.remote.dto.UpdateProfileRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class AvatarRepositoryBackend(
    private val api: AvatarApiService,
    private val sessionManager: SessionManager,
    private val context: Context
) {

    suspend fun uploadAvatar(uri: Uri): String? {
        return try {
            Log.d("AVATAR", "🔵 Iniciando subida")

            val token = sessionManager.getToken()
            if (token.isNullOrEmpty()) {
                Log.e("AVATAR", "❌ TOKEN NULO")
                return null
            }

            val tempFile = uriToFile(uri)
            if (tempFile == null) {
                Log.e("AVATAR", "❌ tempFile NULO")
                return null
            }

            val body = MultipartBody.Part.createFormData(
                "image",
                tempFile.name,
                tempFile.asRequestBody()
            )

            val upload = api.uploadImage("Bearer $token", body)

            val avatarUrl = "https://artelabspa-api.onrender.com" + upload.imageUrl
            Log.d("AVATAR", "URL ABSOLUTA: $avatarUrl")

            api.updateProfile(
                "Bearer $token",
                UpdateProfileRequest(avatarUrl = avatarUrl)
            )

            sessionManager.saveAvatar(avatarUrl)

            return avatarUrl

        } catch (e: Exception) {
            Log.e("AVATAR", "❌ ERROR SUBIENDO AVATAR", e)
            return null
        }
    }

    private fun uriToFile(uri: Uri): File? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val tempFile = File.createTempFile("avatar_", ".jpg", context.cacheDir)
            FileOutputStream(tempFile).use { output ->
                inputStream.copyTo(output)
            }
            tempFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
