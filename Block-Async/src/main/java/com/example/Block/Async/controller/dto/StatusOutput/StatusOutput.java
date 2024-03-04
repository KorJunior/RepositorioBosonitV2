package com.example.Block.Async.controller.dto.StatusOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.CompletableFuture;

@Getter
@Setter
@NoArgsConstructor

public class StatusOutput {
    private int porcentaje;
    private CompletableFuture <String> mensaje;

    public StatusOutput(int procentaje, CompletableFuture <String> mensaje) {
        this.porcentaje = procentaje;
        this.mensaje = mensaje;
    }
}
