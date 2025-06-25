# 📖 API de Textos 

Esta API foi desenvolvida para expor de forma estruturada os textos do site [Momento Espírita](https://www.momento.com.br/pt/textos.php), cuja página não foi originalmente projetada para consumo via API.

---

## 🔍 Descrição

O site **Momento Espírita** possui centenas de mensagens edificantes, porém seu conteúdo está disponível apenas através de uma **página web não estruturada**, com os textos incorporados diretamente no HTML e em scripts JavaScript. Para tornar esse acervo acessível programaticamente, esta API foi criada com um mecanismo de **web scraping inteligente**, capaz de:

- Navegar pela estrutura da página.
- Identificar e extrair os IDs válidos dos textos.
- Fazer parsing do conteúdo HTML e retornar apenas o texto limpo, em formato JSON.

---

## ⚙️ Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3**
- **Jsoup** (para parsing HTML)
- **Railway** (deploy)

---

## 🌐 Endpoints da API

### 🔗 Texto por ID ou Random

**URL:**        

   GET https://apitextos-production.up.railway.app/api/texto?id=6263

   GET https://apitextos-production.up.railway.app/api/texto/random



**Exemplo de resposta:**
```json
{
  "titulo": "A importância da empatia",
  "conteudo": "Empatia é colocar-se no lugar do outro..."
}

