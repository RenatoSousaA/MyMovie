package br.com.fiap.utilities

import java.util.*

class ConverterDate {

    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }


}