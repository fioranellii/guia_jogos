[![Funcionamento do app](https://img.youtube.com/vi/Xd_UAy4RnmM/maxresdefault.jpg)](https://youtu.be/Xd_UAy4RnmM)


# Guia de Jogos

Aplicativo Android para exibir uma lista de jogos, com detalhes de cada jogo, compatível com **modo claro e escuro**. O app utiliza , layouts responsivos e cores consistentes entre light/dark mode.
Desenvolvido na aula de Dispositivos Móveis, do 4º semestre no Instituto Federal Campus Araraquara.

---

## Funcionalidades

- Lista de jogos com imagem, nome, desenvolvedora, gênero e descrição.
- Tela de detalhes do jogo, com:
  - Capa do jogo
  - Nome, desenvolvedora e gênero
  - Descrição completa
  - Botões de acesso rápido para Steam e Wiki
- Botões para alternar entre **tema claro e escuro**.
- Botão de alternar idioma (EN/PT) para o app.
- Layouts totalmente responsivos e adaptáveis ao modo claro e escuro.

---

## Estrutura de Layouts

### MainActivity
- `LinearLayout` principal
- Botões de tema e idioma
- `TextView` com título principal
- `ListView` exibindo os jogos (`item_jogo.xml`)

### item_jogo.xml
- LinearLayout horizontal
- `ImageView` para capa do jogo
- TextViews para:
  - Nome do jogo
  - Desenvolvedora
  - Gênero
  - Descrição curta

### DetalheJogoActivity
- ScrollView com LinearLayout vertical
- `ImageView` da capa
- TextViews de nome, desenvolvedora, gênero e descrição
- Botões: Steam e Wiki
- Botão de voltar
