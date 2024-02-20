package com.example.block7estadventa.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class CacheMethods {
    WebClient webClient;
    public CacheMethods() {
        webClient = WebClient.create();
    }

    @Cacheable("clientes")
    public String convertirDniClienteEnNombre(String idCliente) {
        String url = "http://localhost:8080/clientes/" + idCliente;


        Map<String, Object> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        assert response != null;
        return (String) response.get("nombre");
    }
    @Cacheable("producto")
    public String convertirIdProductoEnNombre(long idProducto) {
        String url = "http://localhost:8080/producto/" + idProducto;

        Map<String, Object> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        assert response != null;
        return (String) response.get("descripcionProducto");
    }
}
