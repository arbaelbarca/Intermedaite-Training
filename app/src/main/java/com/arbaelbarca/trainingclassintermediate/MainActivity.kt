package com.arbaelbarca.trainingclassintermediate

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var rvUsers: RecyclerView
    lateinit var btnAddUsers: Button
    lateinit var progressBar: ProgressBar
    lateinit var btnNavigationPage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rvListUsers)
        btnAddUsers = findViewById(R.id.btnAddUsers)
        progressBar = findViewById(R.id.progressBar)
        btnNavigationPage = findViewById(R.id.btnNavigationPage)
    }

    override fun onResume() {
        super.onResume()
        initial()
    }

    fun initial() {
        progressBar.visibility = View.VISIBLE
        rvUsers.visibility = View.GONE

        val callDataUsers = ApiClient.initApiService().getAllUsers()
        callDataUsers.enqueue(object : Callback<List<ResponseUsers.ResponseUsersItem>> {
            override fun onResponse(
                call: Call<List<ResponseUsers.ResponseUsersItem>>,
                response: Response<List<ResponseUsers.ResponseUsersItem>>
            ) {
                val listData = response.body()

                if (response.isSuccessful) {
                    //initadapter
                    val adapterListUsers =
                        AdapterListUsers(listData as List<ResponseUsers.ResponseUsersItem>,
                            object : OnClickItem {
                                override fun clickItem(any: Any, position: Int) {
                                    val dataItem = any as ResponseUsers.ResponseUsersItem
                                    val intent = Intent(
                                        this@MainActivity,
                                        UserFormUpdateActivity::class.java
                                    )
                                    intent.putExtra("username", dataItem.username)
                                    intent.putExtra("name", dataItem.name)
                                    intent.putExtra("id", dataItem.id)
                                    startActivity(intent)
                                }

                                override fun clickItemDelete(any: Any, position: Int) {
                                    val dataItem = any as ResponseUsers.ResponseUsersItem
                                    deleteDataUsers(dataItem.id.toString())
                                }
                            })

                    rvUsers.adapter = adapterListUsers
                    rvUsers.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvUsers.hasFixedSize()

                    progressBar.visibility = View.GONE
                    rvUsers.visibility = View.VISIBLE

                } else {
                    Toast.makeText(this@MainActivity, "gagal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(
                call: Call<List<ResponseUsers.ResponseUsersItem>>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }
        })


        btnAddUsers.setOnClickListener {
            val intent = Intent(this, UserFormActivity::class.java)
            startActivity(intent)
        }

        btnNavigationPage.setOnClickListener {
            val intent = Intent(this, AllMenuButtonActivity::class.java)
            startActivity(intent)
        }
    }

    fun deleteDataUsers(idUsers: String) {
        val deleteDataItemUsers = ApiClient.initApiService().deleteUsers(idUsers)

        deleteDataItemUsers.enqueue(object : Callback<ResponseSuccess> {
            override fun onResponse(
                call: Call<ResponseSuccess>,
                response: Response<ResponseSuccess>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Berhasil delete data", Toast.LENGTH_SHORT)
                        .show()
                    onResume()
                } else {
                    Toast.makeText(this@MainActivity, "Failed delete data", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseSuccess>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}