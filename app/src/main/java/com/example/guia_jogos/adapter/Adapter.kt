package com.example.guia_jogos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.guia_jogos.databinding.ItemJogoBinding
import com.example.guia_jogos.model.Jogo
import java.util.Locale
import com.example.guia_jogos.R

class JogosAdapter(
    private val listaJogosCompleta: List<Jogo>,
    private val onItemClick: (Jogo) -> Unit
) : RecyclerView.Adapter<JogosAdapter.JogoViewHolder>(), Filterable {

    private var listaJogosAtual: List<Jogo> = listaJogosCompleta

    class JogoViewHolder(val binding: ItemJogoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogoViewHolder {
        val binding = ItemJogoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return JogoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JogoViewHolder, position: Int) {
        val jogo = listaJogosAtual[position]

        try {
            holder.binding.imgCapa.setImageURI(android.net.Uri.parse(jogo.capaUri))
        } catch (e: Exception) {
            holder.binding.imgCapa.setImageResource(R.drawable.ic_image_placeholder)
        }

        holder.binding.tvNome.text = jogo.nome
        holder.binding.tvDesenvolvedora.text = jogo.desenvolvedora
        holder.binding.tvGenero.text = jogo.genero
        holder.binding.tvDescricao.text = jogo.descricao

        holder.binding.root.setOnClickListener {
            onItemClick(jogo)
        }
    }

    override fun getItemCount(): Int {
        return listaJogosAtual.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString().toLowerCase(Locale.ROOT)

                listaJogosAtual = if (charString.isEmpty()) {
                    listaJogosCompleta
                } else {
                    listaJogosCompleta.filter { jogo ->
                        jogo.nome.toLowerCase(Locale.ROOT).contains(charString)
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = listaJogosAtual
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                listaJogosAtual = filterResults?.values as List<Jogo>
                notifyDataSetChanged()
            }
        }
    }
}