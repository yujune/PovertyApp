package com.example.poverty

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_register.*

class register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        textView_AlreadyHaveAnAcc.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        button_register.setOnClickListener {
            saveUser()
        }
    }

    private fun saveUser(){

        if(TextUtils.isEmpty(editText_r_username.text)){
            editText_r_username.setError(getString(R.string.error_value_required))
            return
        }
        if(TextUtils.isEmpty(editText_r_email.text)){
            editText_r_email.setError(getString(R.string.error_value_required))
            return
        }
        if(TextUtils.isEmpty(editText_r_password.text)){
            editText_r_email.setError(getString(R.string.error_value_required))
            return
        }
        val name = editText_r_username.text.toString()
        val email = editText_r_email.text.toString()
        val password = editText_r_password.text.toString()

        val intent = Intent()
        intent.putExtra(EXTRA_NAME, name)
        intent.putExtra(EXTRA_EMAIL, email)
        intent.putExtra(EXTRA_PASS, password)

        setResult(Activity.RESULT_OK, intent)

        finish()
    }

    companion object{
        const val EXTRA_NAME = "com.example.poverty.NAME"
        const val EXTRA_EMAIL = "com.example.poverty.EMAIL"
        const val EXTRA_PASS = "com.example.poverty.PASS"
    }

}
