package api.message.messages.controllers;

import api.message.messages.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TextsController {

    @Autowired
    private ScraperService scraperService;

    // 🔗 Endpoint para texto por ID
    @GetMapping("/texto")
    public Map<String, String> obterTextoPorId(@RequestParam String id) throws IOException {
        return scraperService.pegarTextoPorId(id);
    }

    // 🔗 Endpoint para texto aleatório
    @GetMapping("/texto/random")
    public Map<String, String> obterTextoAleatorio() throws IOException {
        return scraperService.pegarTextoAleatorioPorIntervalo();
    }

    // 🔗 Endpoint para obter todos os IDs válidos
    @GetMapping("/ids")
    public List<String> obterIds() throws IOException {
        return scraperService.coletarIdsValidos();
    }
}



