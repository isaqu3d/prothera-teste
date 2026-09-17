package org.example.util;

import org.example.model.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FuncionarioFormatter {

    private static final Locale LOCALE_BR = new Locale("pt", "BR");

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DecimalFormat FORMATO_NUMERO =
            new DecimalFormat("#,##0.00", new DecimalFormatSymbols(LOCALE_BR));

    public String formatarData(LocalDate data) {
        return data.format(FORMATO_DATA);
    }

    public String formatarNumero(BigDecimal valor) {
        return FORMATO_NUMERO.format(valor);
    }

    public String formatarCompleto(Funcionario funcionario) {
        return String.format("%-10s | %s | %12s | %s",
                funcionario.getNome(),
                formatarData(funcionario.getDataNascimento()),
                formatarNumero(funcionario.getSalario()),
                funcionario.getFuncao());
    }
}