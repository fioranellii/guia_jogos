package com.example.guia_jogos.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guia_jogos.R
import com.example.guia_jogos.adapter.JogoAdapter
import com.example.guia_jogos.databinding.ActivityMainBinding
import com.example.guia_jogos.model.Jogo
import android.content.Intent

class   MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var jogos: List<Jogo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()
    }

    private fun loadData() {
        jogos = listOf(
            Jogo(
                R.drawable.sekiro,
                "Sekiro: Shadows Die Twice",
                "FromSoftware",
                "Ação e aventura",
                "Um jogo de ação no Japão feudal, focado em combate preciso e exploração.",
                "https://store.steampowered.com/app/814380/Sekiro_Shadows_Die_Twice/",
                "https://sekiroshadowsdietwice.wiki.fextralife.com/Sekiro+Shadows+Die+Twice+Wiki"
            ),
            Jogo(
                R.drawable.isaac,
                "The Binding of Isaac",
                "Edmund McMillen",
                "Roguelike",
                "Um RPG de ação e tiro roguelike com elementos de masmorra.",
                "https://store.steampowered.com/app/250900/The_Binding_of_Isaac_Rebirth/",
                "https://bindingofisaacrebirth.fandom.com/wiki/Binding_of_Isaac:_Rebirth_Wiki"
            ),
            Jogo(
                R.drawable.webfshing,
                "Webfishing",
                "Lamedeveloper",
                "Casual",
                "Um jogo de pesca social multijogador, focado em pescar e conversar em salas 3D.",
                "https://store.steampowered.com/app/3071070/WEBFISHING/",
                "https://webfishing.wiki.gg/"
            ),
            Jogo(
                R.drawable.mhw,
                "Monster Hunter: World",
                "Capcom",
                "Ação e aventura",
                "Um RPG em mundo aberto onde o jogador caça monstros no Novo Mundo.",
                "https://store.steampowered.com/app/582010/Monster_Hunter_World/",
                "https://monsterhunterworld.wiki.fextralife.com/Monster+Hunter+World+Wiki"
            ),
            Jogo(
                R.drawable.mouthwashing,
                "Mouthwashing",
                "Critical Reflex, Fangamer",
                "Aventura",
                "Um jogo de terror psicológico em um cargueiro espacial.",
                "https://store.steampowered.com/app/2600580/Mouthwashing/",
                "https://mouthwashing.fandom.com/wiki/Mouthwashing_Wiki"
            ),
            Jogo(
                R.drawable.warframe,
                "Warframe",
                "Digital Extremes",
                "Tiro em terceira pessoa",
                "Um jogo de ação online gratuito onde os jogadores controlam os Tenno.",
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
