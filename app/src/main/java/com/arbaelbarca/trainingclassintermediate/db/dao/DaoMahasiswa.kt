package com.arbaelbarca.trainingclassintermediate.db.dao

import androidx.room.*
import com.arbaelbarca.trainingclassintermediate.db.entity.EntityMahasiswa

@Dao
interface DaoMahasiswa {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData(entityMahasiswa: EntityMahasiswa)


    @Update
    fun updateData(entityMahasiswa: EntityMahasiswa)

    @Delete
    fun deleteData(entityMahasiswa: EntityMahasiswa)

    @Query("select * from EntityMahasiswa")
    fun getAllDataDb(): List<EntityMahasiswa>
}