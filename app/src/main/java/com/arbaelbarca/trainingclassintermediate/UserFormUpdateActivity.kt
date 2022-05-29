package com.arbaelbarca.trainingclassintermediate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFormUpdateActivity : AppCompatActivity() {

    var getUserName = ""
    var getName = ""
    var getId = "0"

    lateinit var edUsername: EditText
    lateinit var edName: EditText
    lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_form_update)

        edUsername = findViewById(R.id.edFormUpdateUsername)
        edName = findViewById(R.id.edFormUpdateName)
        btnUpdate = findViewById(R.id.btnSaveUpdate)

        initial()
    }


    fun initial() {
        getUserName = intent.getStringExtra("username").toString()
        getName = intent.getStringExtra("name").toString()
        getId = intent.getIntExtra("id", 0).toString()

        edUsername.setText(getUserName)
        edName.setText(getName)


        btnUpdate.setOnClickListener {
            val edDataUsername = edUsername.text.toString()
            val edDataName = edName.text.toString()
            updateDataUsers(edDataUsername, edDataName)
        }

    }

    fun updateDataUsers(edGetUsername: String, edGetName: String) {

        val requestUsers = RequestUsers(
            edGetName,
            "",
            edGetUsername
        )

        val putDataUsers = ApiClient.initApiService().updateUsers(getId, requestUsers)

        putDataUsers.enqueue(object : Callback<ResponseSuccess> {
            override fun onResponse(
                call: Call<ResponseSuccess>,
                response: Response<ResponseSuccess>
            ) {
                if (response.isSuccessful) {
                    finish()
                    Toast.makeText(this@UserFormUpdateActivity, "Success update data", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@UserFormUpdateActivity, "Failed update data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSuccess>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}