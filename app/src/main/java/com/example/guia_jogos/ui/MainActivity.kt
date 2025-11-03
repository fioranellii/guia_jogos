package com.example.guia_jogos.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.guia_jogos.R
import com.example.guia_jogos.adapter.JogoAdapter
import com.example.guia_jogos.databinding.ActivityMainBinding
import com.example.guia_jogos.model.Jogo
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var jogos: List<Jogo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()

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

    private fun loadData() {
        jogos = listOf(
            Jogo(
                R.drawable.sekiro,
                getString(R.string.nome_sekiro),
                "FromSoftware",
                getString(R.string.genero_acao_aventura),
                getString(R.string.desc_sekiro),
                "https://store.steampowered.com/app/814380/Sekiro_Shadows_Die_Twice/",
                "https://sekiroshadowsdietwice.wiki.fextralife.com/Sekiro+Shadows+Die+Twice+Wiki"
            ),
            Jogo(
                R.drawable.isaac,
                getString(R.string.nome_isaac),
                "Edmund McMillen",
                getString(R.string.genero_roguelike),
                getString(R.string.desc_isaac),
                "https://store.steampowered.com/app/250900/The_Binding_of_Isaac_Rebirth/",
                "https://bindingofisaacrebirth.fandom.com/wiki/Binding_of_Isaac:_Rebirth_Wiki"
            ),
            Jogo(
                R.drawable.webfshing,
                "Webfishing",
                "Lamedeveloper",
                getString(R.string.genero_casual),
                getString(R.string.desc_webfishing),
                "https://store.steampowered.com/app/3071070/WEBFISHING/",
                "https://webfishing.wiki.gg/"
            ),
            Jogo(
                R.drawable.mhw,
                "Monster Hunter: World",
                "Capcom",
                getString(R.string.genero_acao_aventura),
                getString(R.string.desc_mhw),
                "https://store.steampowered.com/app/582010/Monster_Hunter_World/",
                "https://monsterhunterworld.wiki.fextralife.com/Monster+Hunter+World+Wiki"
            ),
            Jogo(
                R.drawable.mouthwashing,
                "Mouthwashing",
                "Critical Reflex, Fangamer",
                getString(R.string.genero_aventura),
                getString(R.string.desc_mouthwashing),
                "https://store.steampowered.com/app/2600580/Mouthwashing/",
                "https://mouthwashing.fandom.com/wiki/Mouthwashing_Wiki"
            ),
            Jogo(
                R.drawable.warframe,
                "Warframe",
                "Digital Extremes",
                getString(R.string.genero_tiro),
                getString(R.string.desc_warframe),
                "https://store.steampowered.com/app/230410/Warframe/",
                "https://wiki.warframe.com/"
            )
        ).sortedBy { it.nome }
    }

    private fun setupViews() {
        val adapter = JogoAdapter(this, jogos)
        binding.listViewJogos.adapter = adapter
    }

    private fun setupListeners() {
        binding.listViewJogos.setOnItemClickListener { _, _, position, _ ->
            val jogo = jogos[position]
            val intent = Intent(this, DetalheActivity::class.java).apply {
                putExtra("capa", jogo.capa)
                putExtra("nome", jogo.nome)
                putExtra("desenvolvedora", jogo.desenvolvedora)
                putExtra("genero", jogo.genero)
                putExtra("descricao", jogo.descricao)
                putExtra("linkSteam", jogo.linkSteam)
                putExtra("linkWiki", jogo.linkWiki)
            }
            startActivity(intent)
        }
    }
}
