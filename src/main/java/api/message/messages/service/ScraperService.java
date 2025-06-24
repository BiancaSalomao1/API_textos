package api.message.messages.service;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ScraperService {

    // 🔥 Coletar IDs válidos do site
    public List<String> coletarIdsValidos() {
        List<String> letras = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Z");
        List<String> ids = new ArrayList<>();

        for (String letra : letras) {
            try {
                String url = "https://www.momento.com.br/pt/ler_textos.php?let=" + letra;

                Document doc = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0")
                        .timeout(10000)
                        .get();

                Elements links = doc.select("a[href^=ler_texto.php?id=]");

                for (Element link : links) {
                    String href = link.attr("href");
                    String id = href.split("id=")[1].split("&")[0];
                    ids.add(id);
                }

            } catch (HttpStatusException e) {
                System.out.println("❌ Página não encontrada para letra: " + letra + " (404)");
            } catch (IOException e) {
                System.out.println("⚠ Erro ao conectar na letra: " + letra + " - " + e.getMessage());
            } catch (Exception e) {
                System.out.println("⚠ Erro inesperado na letra: " + letra + " - " + e.getMessage());
            }
        }

        if (ids.isEmpty()) {
            throw new RuntimeException("❌ Não foi possível obter nenhum ID. O site pode estar fora do ar ou mudou a estrutura.");
        }

        return ids;
    }

    // 🔥 Buscar texto por ID específico
    public Map<String, String> pegarTextoPorId(String id) throws IOException {
        String url = "https://www.momento.com.br/pt/ler_texto.php?id=" + id;

        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10000)
                .get();

        Element titulo = doc.getElementById("titulo");
        Element conteudo = doc.getElementById("informacoes");

        Map<String, String> resultado = new HashMap<>();
        resultado.put("id", id);
        resultado.put("titulo", titulo != null ? titulo.text() : "Sem título");
        resultado.put("conteudo", conteudo != null ? conteudo.text() : "Conteúdo vazio");

        return resultado;
    }

    // 🔥 Buscar texto aleatório por IDs entre 0002 e 9999
    public Map<String, String> pegarTextoAleatorioPorIntervalo() {
        try {
            Random random = new Random();
            int numero = random.nextInt(4998) + 100; // Gera número de 2 até 9999
            String idFormatado = String.format("%04d", numero); // Formata como 4 dígitos, ex.: 0002, 0234

            System.out.println("🔢 ID sorteado: " + idFormatado);

            return pegarTextoPorId(idFormatado);

        } catch (Exception e) {
            Map<String, String> erro = new HashMap<>();
            erro.put("status", "erro");
            erro.put("mensagem", "❌ Erro ao buscar texto aleatório: " + e.getMessage());
            return erro;
        }
    }

}
