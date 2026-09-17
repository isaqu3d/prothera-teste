package org.example.service;

import org.example.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class FuncionarioService {
    private static final int ESCALA_MONETARIA = 2;

    public void removerPorNome(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        BigDecimal multiplicador = BigDecimal.ONE.add(percentual);

       funcionarios.forEach(funcionario -> funcionario.setSalario(
               funcionario.getSalario().multiply(multiplicador).setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP)
       ));
    }



}
