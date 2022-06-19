package com.arbaelbarca.trainingclassintermediate.db.dao

import androidx.room.*
import com.arbaelbarca.trainingclassintermediate.db.entity.EntityMahasiswa

@Dao
interface DaoMahasiswa {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(entityMahasiswa: EntityMahasiswa)
}