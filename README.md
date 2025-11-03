# Guia de Jogos

Aplicativo Android para exibir uma lista de jogos, com detalhes de cada jogo, compatível com **modo claro e escuro**. O app utiliza **Material3**, layouts responsivos e cores consistentes entre light/dark mode.

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

---

## Temas e Cores

### Cores definidas (`colors.xml`)

```xml
<color name="purple_200">#BB86FC</color>
<color name="purple_500">#6750A4</color>
<color name="purple_700">#5E35B1</color>

<color name="white">#FFFFFF</color>
<color name="black">#000000</color>

<color name="background_light">#F5F5F5</color>
<color name="background_dark">#121212</color>

<color name="outline_variant_light">#D1C4E9</color>
<color name="outline_variant_dark">#2C2C2C</color>
