package com.example.Block.Async.ciudadservice;

import com.example.Block.Async.controller.CiudadController;
import com.example.Block.Async.controller.dto.StatusOutput.ResponseMessage;
import com.example.Block.Async.controller.dto.StatusOutput.StatusOutput;
import com.example.Block.Async.domain.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.Block.Async.controller.CiudadController.ciudades;

@Service
@RequiredArgsConstructor
public class CiudadService {
    HashMap<Integer, AtomicInteger> progreso = new HashMap<>();


    CompletableFuture<Integer> valor;

    @Async
    public CompletableFuture<String> addCiudad(String nombre, int id) {
        StatusOutput statusOutput = ciudades.get(id);
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        progreso.put(id, new AtomicInteger(0));
        int numero = 25;
        for (int i = 0; i < numero; i++) {
            try {
                Thread.sleep(2000);

                if (Math.random() < 0.05) {
                    completableFuture.completeExceptionally(new RuntimeException("Error desconocido"));
                    return completableFuture;
                }
            } catch (InterruptedException e) {
                completableFuture.completeExceptionally(e);
                return completableFuture;
            }
            statusOutput.setPorcentaje(i);
            System.out.println("Vamos equipo, ya casi terminamos");
        }

        completableFuture.complete("termine!!");
        return completableFuture;
    }

    public ResponseMessage getProgress(int id) {
        StatusOutput statusOutput = ciudades.get(id);
        if (!statusOutput.getMensaje().isDone()) {
            double porcentaje = (double) statusOutput.getPorcentaje() / 25 * 100;
            System.out.println("Esto es un porcentaje " + porcentaje);
            return new ResponseMessage("Cargando", porcentaje + " %");
        } else if (statusOutput.getMensaje().isCompletedExceptionally()) {
            return new ResponseMessage("Error", "Error desconocido");
        } else {
            try {
                String msg = statusOutput.getMensaje().get();
                return new ResponseMessage(msg, "100%");
            } catch (InterruptedException | ExecutionException e) {
                return new ResponseMessage("Error", "Error desconocido");
            }
        }
    }
}
