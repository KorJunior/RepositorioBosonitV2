package com.example.block18springai.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChatEntity {
    @Id
    @GeneratedValue
    private int id;
    private String message;
    private String response;


    public ChatEntity(String message, String response) {
        this.message = message;
        this.response = response;
    }

    @Override
    public String toString() {
        return response + "\t" +
                "Mensaje :\t" +
                message + "\t" + "\n";
    }
}
