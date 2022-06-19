package com.arbaelbarca.trainingclassintermediate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.arbaelbarca.trainingclassintermediate.db.AppDatabase
import com.arbaelbarca.trainingclassintermediate.db.entity.EntityMahasiswa

class FormMahasiswaActivity : AppCompatActivity() {

    lateinit var edName: EditText
    lateinit var edUsername: EditText
    lateinit var edEmail: EditText

    lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_mahasiswa)

        edName = findViewById(R.id.edInputNamaMahasiswa)
        edUsername = findViewById(R.id.edInputUsernameMahasiswa)
        edEmail = findViewById(R.id.edInputEmailMahasiswa)
        btnSave = findViewById(R.id.btnSaveDataMahasiswa)

        btnSave.setOnClickListener {
            val getNama = edName.text.toString()
            val getUsername = edUsername.text.toString()
            val getEmail = edEmail.text.toString()

            savingDataMahasiswa(getNama, getUsername, getEmail)
        }

    }

    private fun savingDataMahasiswa(nama: String, username: String, email: String) {
        val dataMahasiswa = EntityMahasiswa(
            0,
            username,
            nama,
            email
        )

        AppDatabase.getDatabaseInstance(this)
            .daoMahasiswa().insertData(dataMahasiswa)
    }
}