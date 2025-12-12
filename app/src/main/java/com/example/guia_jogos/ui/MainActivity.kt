package com.example.guia_jogos.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guia_jogos.adapter.JogosAdapter
import com.example.guia_jogos.databinding.ActivityMainBinding
import com.example.guia_jogos.model.Jogo
import com.example.guia_jogos.AppDatabase
import com.example.guia_jogos.JogoDao
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var jogoDao: JogoDao
    private lateinit var jogosAdapter: JogosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        jogoDao = AppDatabase.getDatabase(this).jogoDao()

        setupRecyclerView(emptyList())
        setupListeners()
        setupFiltroBusca()

        observeData()

        binding.btnTema.setOnClickListener {
            val mode = AppCompatDelegate.getDefaultNightMode()
            if (mode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        binding.btnIdioma.setOnClickListener {
            val current = resources.configuration.locales[0]
            val newLocale = if (current.language == "pt") Locale("en") else Locale("pt")
            Locale.setDefault(newLocale)
            val config = Configuration()
            config.setLocale(newLocale)
            resources.updateConfiguration(config, resources.displayMetrics)
            recreate()
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            jogoDao.getAll().collectLatest { listaJogos ->
                updateAdapter(listaJogos)
            }
        }
    }

    private fun setupRecyclerView(listaJogos: List<Jogo>) {
        jogosAdapter = JogosAdapter(listaJogos) { jogo ->
            val intent = Intent(this, DetalheActivity::class.java).apply {
                putExtra("capaUri", jogo.capaUri)
                putExtra("nome", jogo.nome)
                putExtra("desenvolvedora", jogo.desenvolvedora)
                putExtra("genero", jogo.genero)
                putExtra("descricao", jogo.descricao)
                putExtra("linkSteam", jogo.linkSteam)
                putExtra("linkWiki", jogo.linkWiki)
            }
            startActivity(intent)
        }

        binding.recyclerViewJogos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewJogos.adapter = jogosAdapter
    }

    private fun updateAdapter(novaLista: List<Jogo>) {
        setupRecyclerView(novaLista)
    }

    private fun setupListeners() {
        binding.btnCadastro.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }

    private fun setupFiltroBusca() {
        binding.etFiltroNome.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                jogosAdapter.filter.filter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}