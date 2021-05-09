package com.jaemin.features.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build

fun Uri.getBitmap(context: Context) : Bitmap?{
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

        ImageDecoder.decodeBitmap(
            ImageDecoder.createSource(
                context.contentResolver,
                this
            )
        )

    } else {
        context
            .contentResolver
            .openInputStream(this)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }

    }
}