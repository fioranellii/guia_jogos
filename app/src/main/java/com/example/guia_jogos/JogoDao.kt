package com.example.guia_jogos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.guia_jogos.model.Jogo
import kotlinx.coroutines.flow.Flow

@Dao
interface JogoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jogo: Jogo): Long

    @Query("SELECT * FROM tabela_jogos ORDER BY nome ASC")
    fun getAll(): Flow<List<Jogo>>
}