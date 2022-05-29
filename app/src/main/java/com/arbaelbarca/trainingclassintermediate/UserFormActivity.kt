package com.arbaelbarca.trainingclassintermediate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserFormActivity : AppCompatActivity() {

    lateinit var btnSave: Button
    lateinit var edUsername: EditText
    lateinit var edName: EditText
    lateinit var edPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_form)

        initial()
    }

    fun initial() {
        btnSave = findViewById(R.id.btnSave)
        edUsername = findViewById(R.id.edFormUsername)
        edName = findViewById(R.id.edFormName)
        edPassword = findViewById(R.id.edFormPassword)

        btnSave.setOnClickListener {
            val getUsername = edUsername.text.toString()
            val getName = edName.text.toString()
            val getPassword = edPassword.text.toString()

            savingData(getUsername, getName, getPassword)

        }
    }


    fun savingData(
        getUsername: String,
        getName: String,
        getPassword: String
    ) {

        val requestUsers = RequestUsers(
            getName,
            getPassword,
            getUsername
        )

        val callCreateUsers = ApiClient.initApiService().createUsers(requestUsers)

        callCreateUsers.enqueue(object : Callback<ResponseSuccess> {
            override fun onResponse(
                call: Call<ResponseSuccess>,
                response: Response<ResponseSuccess>
            ) {
                if (response.isSuccessful) {
                    val getResponse = response.body()
                    val getMessage = getResponse?.message
                    Toast.makeText(this@UserFormActivity, getMessage, Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@UserFormActivity, "Data tidak berhasil di buat", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ResponseSuccess>, t: Throwable) {

            }

        })

    }
}