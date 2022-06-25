package com.arbaelbarca.trainingclassintermediate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.arbaelbarca.trainingclassintermediate.db.AppDatabase
import com.arbaelbarca.trainingclassintermediate.db.entity.EntityMahasiswa

class FormUpdateMahasiswaActivity : AppCompatActivity() {

    lateinit var edEmail: EditText
    lateinit var edName: EditText
    lateinit var edUsername: EditText
    lateinit var btnUpdate: Button

    var entityMahasiswa: EntityMahasiswa? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_update_mahasiswa)

        edEmail = findViewById(R.id.edInputUpdateEmailMahasiswa)
        edName = findViewById(R.id.edInputUpdateNamaMahasiswa)
        edUsername = findViewById(R.id.edInputUpdateUsernameMahasiswa)
        btnUpdate = findViewById(R.id.btnUpdateData)

        entityMahasiswa = intent.getParcelableExtra("dataMahasiswa")

        edEmail.setText(entityMahasiswa?.email)
        edName.setText(entityMahasiswa?.name)
        edUsername.setText(entityMahasiswa?.username)


        btnUpdate.setOnClickListener {
            val getEmail = edEmail.text.toString()
            val getName = edName.text.toString()
            val getUsername = edUsername.text.toString()
            updateDataMahasiswa(
                getEmail,
                getName, getUsername
            )
        }
    }


    fun updateDataMahasiswa(getEmail: String, getName: String, getUsername: String) {
        val entityMahasiswa = EntityMahasiswa(
            entityMahasiswa?.id!!,
            getUsername,
            getName,
            getEmail
        )

        AppDatabase.getDatabaseInstance(this)
            .daoMahasiswa().updateData(entityMahasiswa)
    }
}