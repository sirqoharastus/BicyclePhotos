package com.abdulqohar.bicyclephotos.presentation.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ShowToast(message: String) {
    val mContext = LocalContext.current
    LaunchedEffect(true) {
        Toast.makeText(
            mContext,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}