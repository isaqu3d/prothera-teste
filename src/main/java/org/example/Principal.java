package org.example;

import org.example.model.Funcionario;
import org.example.model.Pessoa;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Principal {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat FORMATO_SALARIO =
            new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static void main(String[] args) {



        List<Funcionario> funcionarios = new ArrayList<>(List.of(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João",    LocalDate.of(1990,  5, 12), new BigDecimal("2284.38"),  "Operador"),
                new Funcionario("Caio",    LocalDate.of(1961,  5,  2), new BigDecimal("9836.14"),  "Coordenador"),
                new Funcionario("Miguel",  LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice",   LocalDate.of(1995,  1,  5), new BigDecimal("2234.68"),  "Recepcionista"),
                new Funcionario("Heitor",  LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"),  "Operador"),
                new Funcionario("Arthur",  LocalDate.of(1993,  3, 31), new BigDecimal("4071.84"),  "Contador"),
                new Funcionario("Laura",   LocalDate.of(1994,  7,  8), new BigDecimal("3017.45"),  "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003,  5, 24), new BigDecimal("1606.85"),  "Eletricista"),
                new Funcionario("Helena",  LocalDate.of(1996,  9,  2), new BigDecimal("2799.93"),  "Gerente")
        ));

        System.out.println("=== 3.3 - Lista de funcionários ===");
        for (Funcionario funcionario : funcionarios) {
            System.out.printf("%-10s | %s | %12s | %s%n",
                    funcionario.getNome(),
                    funcionario.getDataNascimento().format(FORMATO_DATA),
                    FORMATO_SALARIO.format(funcionario.getSalario()),
                    funcionario.getFuncao());
        }

        System.out.println("Funcionarios cadstrado: " + funcionarios.size());

    }
}