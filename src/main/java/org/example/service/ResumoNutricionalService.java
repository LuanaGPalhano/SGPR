package org.example.service;

import org.example.DTO.ResumoNutricionalResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@Service
public class ResumoNutricionalService {
    private final WebClient openAIClient;
    private final String modelo;

    public ResumoNutricionalService(
        @Value("${openai.api.url:}") String apiUrl,
        @Value("${openai.api.key: chave_aqui }") String apiKey,
        @Value("${openai.model:}") String modelo) {

        this.openAIClient = WebClient.builder()
           .baseUrl(apiUrl)
           .defaultHeader("Authorization", "Bearer " + apiKey)
           .defaultHeader("Content-Type", "application/json")
           .clientConnector(new ReactorClientHttpConnector(
                HttpClient.create()
                    .responseTimeout(Duration.ofSeconds(10))
           ))
           .build(); 

        this.modelo = modelo;
    }

    public ResumoNutricionalResponse gerarResumo(List<Map<String, String>>porcoes){
        String prompt = criarPrompt(porcoes);

        Map<String, Object> requestBody = Map.of(
            "model", modelo,
            "messages", List.of(
                Map.of("role", "system", "content", 
                "Você é um assistente nutricional. Analise as porções e gere um resumo nutricional objetivo"),  
                Map.of("role", "user", "content", prompt)
            ),
            "temperature", 0.5
        );

        try{
            Map<String, Object> response = openAIClient.post()
            .uri("/chat/completions")
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Map.class)
            .block();

            System.out.println("Resposta da API: " + response);

            List<?> choices = (List<?>) response.get("choices");
            if(choices != null && !choices.isEmpty()){
                Map<?, ?> message = (Map<?, ?>) ((Map<?, ?>) choices.get(0)).get("message");
                String content = (String) message.get("content");

                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(content, ResumoNutricionalResponse.class);
            }
        } catch(Exception e){
            return new ResumoNutricionalResponse(
            "erro ao gerar resumo: " + e.getMessage(),
            0,0,0,0,0,0,"",  "", "", true
            );
        }

        return new ResumoNutricionalResponse(
        "Nenhum resumo retornado",
        0,0,0,0,0,0, "", "", "", true);
    }

    private String criarPrompt(List<Map<String, String>> porcoes) {
        StringBuilder sb = new StringBuilder("Analise a seguinte refeição:\n");
        for (Map<String, String> porcao : porcoes) {
            sb.append("- ").append(porcao.get("alimento"))
              .append(" (").append(porcao.get("quantidade")).append(")\n");
        }
        sb.append("\nRetorne em JSON os campos: "+
        "energia_kcal, " +
        "carboidratos_g, " + 
        "proteinas_g, " +
        "gorduras_g, " +
        "fibras_g, " +
        "sodio_mg, beneficios, " +
        "observacoes, " +
        "sugestoes_substituicao");
        return sb.toString();
    }

}
