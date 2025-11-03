package com.example.guia_jogos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.guia_jogos.databinding.ItemJogoBinding
import com.example.guia_jogos.model.Jogo

class JogoAdapter(
    context: Context,
    private val lista: List<Jogo>
) : ArrayAdapter<Jogo>(context, 0, lista) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemJogoBinding
        val itemView: View

        if (convertView == null) {
            binding = ItemJogoBinding.inflate(LayoutInflater.from(context), parent, false)
            itemView = binding.root
            itemView.tag = binding
        } else {
            itemView = convertView
            binding = itemView.tag as ItemJogoBinding
        }

        val jogo = lista[position]
        binding.imgCapa.setImageResource(jogo.capa)
        binding.tvNome.text = jogo.nome
        binding.tvDesenvolvedora.text = jogo.desenvolvedora
        binding.tvGenero.text = jogo.genero
        binding.tvDescricao.text = jogo.descricao

        return itemView
    }
}
