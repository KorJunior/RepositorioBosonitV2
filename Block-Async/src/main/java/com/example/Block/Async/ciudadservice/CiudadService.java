package com.example.Block.Async.ciudadservice;

import com.example.Block.Async.controller.CiudadController;
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
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            statusOutput.setPorcentaje(i);
            System.out.println("Vamos equipo, ya casi terminamos");
        }


        completableFuture.complete("termine!!");
        return completableFuture;
    }

    //
//    void cargaCiudad(String no  int numero = 25;
//        AtomicInteger progress = progreso.get(id);
//
//        for (int i = 0; i < numero; i++) {
//            Thread.sleep(2000);
//            progress.getAndIncrement();
//            progreso.put(id, progress);
//            System.out.println("Vamos equipo, ya casi terminamos");
//        }
//        ciudades.put(id, nombre);
//
//        System.out.println("Kebab");
//    }

    public String getProgress(int id) {
        StatusOutput statusOutput = ciudades.get(id);
        if (!statusOutput.getMensaje().isDone()) {
            double porcentaje = (double) statusOutput.getPorcentaje() /25*100;
            System.out.println("Esto es un porcentaje " +porcentaje);
            return "" + porcentaje+" %";
        }
        try {
            String msg = statusOutput.getMensaje().get();
            return msg;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
