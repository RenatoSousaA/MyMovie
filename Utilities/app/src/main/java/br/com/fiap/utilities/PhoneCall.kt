package br.com.fiap.utilities

import android.content.Intent
import android.net.Uri

class PhoneCall {

    fun dialNumber(phoneNumber: String?): Intent {
        return Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber))
    }

}