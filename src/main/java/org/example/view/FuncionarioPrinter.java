package org.example.view;

import org.example.model.Funcionario;
import org.example.util.FuncionarioFormatter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FuncionarioPrinter {

    private final FuncionarioFormatter formatter = new FuncionarioFormatter();

    public void imprimirTitulo(String titulo) {
        System.out.println("\n=== " + titulo + " ===");
    }

    public void imprimirLista(String titulo, List<Funcionario> funcionarios) {
        imprimirTitulo(titulo);

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado.");
            return;
        }

        funcionarios.forEach(funcionario ->
                System.out.println(formatter.formatarCompleto(funcionario)));
    }

    public void imprimirAgrupados(String titulo, Map<String, List<Funcionario>> porFuncao) {
        imprimirTitulo(titulo);

        porFuncao.forEach((funcao, lista) -> {
            System.out.println("\n" + funcao + ":");
            lista.forEach(funcionario ->
                    System.out.println("  " + formatter.formatarCompleto(funcionario)));
        });
    }

    public void imprimirMaisVelho(String titulo, Optional<Funcionario> funcionario) {
        imprimirTitulo(titulo);

        funcionario.ifPresentOrElse(
                f -> System.out.printf("Nome: %s | Idade: %d anos%n", f.getNome(), f.getIdade()),
                () -> System.out.println("Lista vazia.")
        );
    }

    public void imprimirTotalSalarios(String titulo, BigDecimal total) {
        imprimirTitulo(titulo);
        System.out.println("Total: R$ " + formatter.formatarNumero(total));
    }

    public void imprimirSalariosMinimos(String titulo, Map<Funcionario, BigDecimal> quantidades) {
        imprimirTitulo(titulo);

        quantidades.forEach((funcionario, quantidade) ->
                System.out.printf("%-10s | %12s | %s salários mínimos%n",
                        funcionario.getNome(),
                        formatter.formatarNumero(funcionario.getSalario()),
                        formatter.formatarNumero(quantidade))
        );
    }
}