package com.arbaelbarca.trainingclassintermediate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arbaelbarca.trainingclassintermediate.db.AppDatabase
import com.arbaelbarca.trainingclassintermediate.db.entity.EntityMahasiswa
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson

class ListRoomActivity : AppCompatActivity() {

    lateinit var rvList: RecyclerView
    lateinit var btnAddFloat: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_room)

        rvList = findViewById(R.id.rvListRoom)
        btnAddFloat = findViewById(R.id.floadBtnAddRoom)


    }

    override fun onResume() {
        super.onResume()
        initial()
    }

    private fun initial() {
        val listEntityMahasiswa = AppDatabase.getDatabaseInstance(this)
            .daoMahasiswa().getAllDataDb()

        println("respon List Mahasiswa ${Gson().toJson(listEntityMahasiswa)}")

        val adapterListRoom = AdapterListRoom(listEntityMahasiswa as MutableList<EntityMahasiswa>,
            object : OnClickItem {
                override fun clickItem(any: Any, position: Int) {
                    val dataMahasiswa = any as EntityMahasiswa
                    val intent =
                        Intent(this@ListRoomActivity, FormUpdateMahasiswaActivity::class.java)
                    intent.putExtra("dataMahasiswa", dataMahasiswa)
                    startActivity(intent)
                }

                override fun clickItemDelete(any: Any, position: Int) {
                    val dataMahasiswa = any as EntityMahasiswa
                    AppDatabase.getDatabaseInstance(this@ListRoomActivity)
                        .daoMahasiswa().deleteData(dataMahasiswa)
                    onResume()
                }

            })

        rvList.layoutManager = LinearLayoutManager(this)
        rvList.hasFixedSize()
        rvList.adapter = adapterListRoom


        btnAddFloat.setOnClickListener {
            val intent = Intent(this, FormMahasiswaActivity::class.java)
            startActivity(intent)
        }
    }
}