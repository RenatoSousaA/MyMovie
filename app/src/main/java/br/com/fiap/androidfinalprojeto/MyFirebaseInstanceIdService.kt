package br.com.fiap.androidfinalprojeto

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseInstanceIdService : FirebaseMessagingService()  {

    val TAG = "PushNotifService"
    lateinit var name: String

    fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Token perangkat ini: ${token}")
    }

}