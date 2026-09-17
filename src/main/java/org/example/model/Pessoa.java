package org.example.model;

import java.time.LocalDate;

public class Pessoa {
    private String nome;
    private LocalDate data;

    public Pessoa(String nome, LocalDate data) {
        this.nome = nome;
        this.data = data;
    }
}
