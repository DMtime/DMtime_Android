package com.jaemin.features.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build

class BitmapManager {
    companion object {
        fun getBitmap(context: Context, imageUri: Uri): Bitmap? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        context.contentResolver,
                        imageUri
                    )
                )

            } else {
                context
                    .contentResolver
                    .openInputStream(imageUri)?.use { inputStream ->
                        BitmapFactory.decodeStream(inputStream)
                    }

            }
        }
    }
}