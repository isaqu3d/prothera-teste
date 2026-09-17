package org.example.service;

import org.example.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public List<Funcionario> aniversariantesNosMeses(List<Funcionario> funcionarios, int... meses) {
        return funcionarios.stream().filter(funcionario -> {
            int mes = funcionario.getDataNascimento().getMonthValue();
            for (int mesBuscado : meses) {
                if (mes == mesBuscado) {
                    return true;
                }
            }
            return false;
        }).toList();
    }

    public Optional<Funcionario> maisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento));
    }

    public List<Funcionario> ordernarPorNome(List<Funcionario> funcionarios) {
        return funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).toList();
    }

    public BigDecimal totalSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal quantidadeSalarioMinimos(Funcionario funcionario, BigDecimal salarioMinimo) {
        return funcionario.getSalario().divide(salarioMinimo, ESCALA_MONETARIA, RoundingMode.HALF_UP);
    }

}
