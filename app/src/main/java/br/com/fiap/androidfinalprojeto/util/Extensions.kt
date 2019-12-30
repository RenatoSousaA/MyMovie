package br.com.fiap.androidfinalprojeto.util

import android.content.Context
import android.widget.EditText
import br.com.fiap.androidfinalprojeto.R
import br.com.fiap.androidfinalprojeto.util.ResourceStringUtil.Companion.enforceGetString
import kotlinx.android.synthetic.main.app_bar_main.view.*
import java.lang.Exception

class ResourceStringUtil {
    companion object {
        fun Int.enforceGetString(context: Context?): String  {
            if(context==null) return ""
            return context.getResources().getString(this)
         }
    }
}

class EditTextUtil {
    companion object {
        fun EditText.validate(context: Context?)  {
            if(context==null) return
            if(this.text.isEmpty()) {
                this.requestFocus()
                if(this.hint.isEmpty())
                    throw Exception(R.string.field_must_be_completed.enforceGetString(context))
                 else
                    throw Exception(this.hint.toString()+" "+R.string.is_required.enforceGetString(context))
            }
        }
    }
}
