package com.azatberdimyradov.spacey.core

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import android.util.Log
import java.io.File
import java.lang.Exception
import javax.inject.Inject

class DownloadImage @Inject constructor(private val context: Context) {

    fun download(fileName: String, imageUrl: String) {
        try {
            var downloadManage: DownloadManager? = null
            downloadManage = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val downloadUri = Uri.parse(imageUrl)
            val request = DownloadManager.Request(downloadUri)
            request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_MOBILE or
                        DownloadManager.Request.NETWORK_MOBILE
            )
                .setAllowedOverRoaming(false)
                .setTitle(fileName)
                .setMimeType("image/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES, File.separator + fileName + ".jpg"
                )
            downloadManage.enqueue(request)
        } catch (e: Exception) {
            Log.d("Download Image", e.message ?: "Something went wrong")
        }
    }
}