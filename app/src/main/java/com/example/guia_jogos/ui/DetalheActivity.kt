package com.example.guia_jogos.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guia_jogos.databinding.ActivityDetalheBinding

class DetalheActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val capa = intent.getIntExtra("capa", 0)
        val nome = intent.getStringExtra("nome")
        val desenvolvedora = intent.getStringExtra("desenvolvedora")
        val genero = intent.getStringExtra("genero")
        val descricao = intent.getStringExtra("descricao")
        val linkSteam = intent.getStringExtra("linkSteam")
        val linkWiki = intent.getStringExtra("linkWiki")

        binding.imgCapaDetalhe.setImageResource(capa)
        binding.tvNomeDetalhe.text = nome
        binding.tvDesenvolvedoraDetalhe.text = desenvolvedora
        binding.tvGeneroDetalhe.text = genero
        binding.tvDescricaoDetalhe.text = descricao

        binding.btnVoltar.setOnClickListener { finish() }
        // Botão Steam
        binding.btnSteam.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkSteam))
            startActivity(intent)
        }
        binding.btnWiki.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkWiki))
            startActivity(intent)
        }
    }
}
