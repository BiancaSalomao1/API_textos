package api.message.messages.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TesteController {

    @GetMapping("/api/teste")
    public Map<String, String> teste(@RequestParam(required = false, defaultValue = "world") String nome) {
        return Map.of(
                "mensagem", "Olá, " + nome,
                "status", "funcionando"
        );
    }
}
