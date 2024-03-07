package com.example.Block.Async.controller;

import com.example.Block.Async.ciudadservice.CiudadService;
import com.example.Block.Async.controller.dto.StatusOutput.ResponseMessage;
import com.example.Block.Async.controller.dto.StatusOutput.StatusOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/ciudad")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CiudadController {

    private final CiudadService service;
    AtomicInteger idTicket = new AtomicInteger(0);
    public static HashMap<Integer, StatusOutput> ciudades = new HashMap<>();


    @PostMapping("/add")
    public int addCiudad(@RequestParam String nombre){
        int id =idTicket.incrementAndGet();
        ciudades.put(id,new StatusOutput(0,service.addCiudad(nombre,id)));

        return id;
    }

    @GetMapping("/progress")
    public ResponseMessage getProgress(@RequestParam int id) {
        return service.getProgress(id);
    }

}
