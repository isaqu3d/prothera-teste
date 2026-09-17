package org.example;

import org.example.model.Funcionario;
import org.example.service.FuncionarioService;
import org.example.view.FuncionarioPrinter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
public class Principal {

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
    private static final BigDecimal PERCENTUAL_AUMENTO = new BigDecimal("0.10");

    public static void main(String[] args) {

        FuncionarioService service = new FuncionarioService();
        FuncionarioPrinter printer = new FuncionarioPrinter();

        List<Funcionario> funcionarios = new ArrayList<>(List.of(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        printer.imprimirLista(" Lista de TODOS os funcionários", funcionarios);

        // 3.2 - Remover o funcionário "João" da lista
        service.removerPorNome(funcionarios, "João");

        // 3.3 - Imprimir todos os funcionários com todas as suas informações
        printer.imprimirLista("3.3 - Lista de funcionários", funcionarios);

        // 3.4 - Aplicar 10% de aumento de salário e atualizar a lista
        service.aplicarAumento(funcionarios, PERCENTUAL_AUMENTO);
        printer.imprimirLista("3.4 - Após aumento de 10%", funcionarios);

        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = service.agruparPorFuncao(funcionarios);

        // 3.6 - Imprimir os funcionários agrupados por função
        printer.imprimirAgrupados("3.6 - Funcionários por função", funcionariosPorFuncao);

        // 3.8 - Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        printer.imprimirLista("3.8 - Aniversariantes de outubro e dezembro",
                service.aniversariantesNosMeses(funcionarios, 10, 12));

        // 3.9 - Imprimir o funcionário com a maior idade (nome e idade)
        printer.imprimirMaisVelho("3.9 - Funcionário com maior idade",
                service.maisVelho(funcionarios));

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        printer.imprimirLista("3.10 - Funcionários em ordem alfabética",
                service.ordenarPorNome(funcionarios));

        // 3.11 - Imprimir o total dos salários dos funcionários
        printer.imprimirTotalSalarios("3.11 - Total dos salários",
                service.totalSalarios(funcionarios));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        Map<Funcionario, BigDecimal> salariosMinimos = new LinkedHashMap<>();
        funcionarios.forEach(funcionario ->
                salariosMinimos.put(funcionario,
                        service.quantidadeSalariosMinimos(funcionario, SALARIO_MINIMO)));

        printer.imprimirSalariosMinimos("3.12 - Salários mínimos por funcionário", salariosMinimos);

    }
}