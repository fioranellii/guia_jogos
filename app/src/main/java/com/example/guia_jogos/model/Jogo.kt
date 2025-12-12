package com.example.guia_jogos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.guia_jogos.R

@Entity(tableName = "tabela_jogos")
data class Jogo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val capaUri: String,

    val nome: String,
    val desenvolvedora: String,
    val genero: String,
    val descricao: String,
    val linkSteam: String,
    val linkWiki: String
)