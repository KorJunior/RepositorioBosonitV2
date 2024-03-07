package com.example.Block.Async.controller.dto.StatusOutput;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CiudadRestController {
    @GetMapping()
    public String index() {
        return "index";
    }
}
