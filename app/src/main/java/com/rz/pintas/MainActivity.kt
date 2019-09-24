package com.rz.pintas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jacksonandroidnetworking.JacksonParserFactory
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient().newBuilder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
        AndroidNetworking.initialize(this, client)
        AndroidNetworking.setParserFactory(JacksonParserFactory())
        login_button.setOnClickListener{
            login()
        }
        logout_button.setOnClickListener{
            logout()
        }
    }

    private fun login(){
        AndroidNetworking.post("http://pintas.unpad.ac.id/ac_portal/login.php")
            .addBodyParameter("userName", username.text.toString())
            .addBodyParameter("pwd", password.text.toString())
            .addBodyParameter("rememberPwd", "0")
            .addBodyParameter("opr", "pwdLogin")
            .setTag("Login Request")
            .addHeaders("Content-Type","application/x-www-form-urlencoded")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    toast(response.toString())
                }

                override fun onError(anError: ANError?) {
                    toast(anError.toString())
                }
            })
    }

    private fun logout(){
        AndroidNetworking.post("http://pintas.unpad.ac.id/ac_portal/login.php")
            .addBodyParameter("opr", "logout")
            .setTag("Logout Request")
            .addHeaders("Content-Type","application/x-www-form-urlencoded")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    toast(response.toString())
                }

                override fun onError(anError: ANError?) {
                    toast(anError.toString())
                }
            })
    }

    private fun toast(text: String) = Toast
        .makeText(this, text, Toast.LENGTH_SHORT).show()

}
