package com.example.guia_jogos.ui // A Activity está em 'ui'

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.guia_jogos.databinding.ActivityCadastroBinding
import com.example.guia_jogos.model.Jogo
import com.example.guia_jogos.AppDatabase
import com.example.guia_jogos.JogoDao
import kotlinx.coroutines.launch

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private var imagemUri: Uri? = null

    private lateinit var jogoDao: JogoDao

    private val seletorImagemLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            imagemUri = data?.data
            binding.imgCapaPreview.setImageURI(imagemUri)

            imagemUri?.let {
                contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        jogoDao = AppDatabase.getDatabase(this).jogoDao()

        binding.btnEscolherImagem.setOnClickListener {
            abrirGaleriaParaSelecao()
        }

        binding.btnSalvarJogo.setOnClickListener {
            salvarNovoJogo()
        }
    }

    private fun abrirGaleriaParaSelecao() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        seletorImagemLauncher.launch(intent)
    }

    private fun salvarNovoJogo() {
        val nome = binding.etNome.text.toString().trim()
        val desenvolvedora = binding.etDesenvolvedora.text.toString().trim()
        val genero = binding.etGenero.text.toString().trim()
        val descricao = binding.etDescricao.text.toString().trim()
        val linkSteam = binding.etLinkSteam.text.toString().trim()
        val linkWiki = binding.etLinkWiki.text.toString().trim()
        val uri = imagemUri

        if (nome.isEmpty() || genero.isEmpty() || uri == null) {
            Toast.makeText(this, "Preencha Nome, Gênero e escolha a Capa.", Toast.LENGTH_LONG).show()
            return
        }

        val novoJogo = Jogo(
            capaUri = uri.toString(),
            nome = nome,
            desenvolvedora = desenvolvedora,
            genero = genero,
            descricao = descricao,
            linkSteam = linkSteam,
            linkWiki = linkWiki
        )

        lifecycleScope.launch {
            val idSalvo = jogoDao.insert(novoJogo)

            if (idSalvo > 0) {
                Toast.makeText(this@CadastroActivity, "Jogo '${novoJogo.nome}' salvo!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@CadastroActivity, "Erro ao salvar o jogo.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}