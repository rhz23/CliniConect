package br.com.rzaninelli.CliniConect.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validador {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        //verificar invalidos conhecidos
        if (cpf.equals("11111111111") ||
                cpf.equals("22222222222") ||
                cpf.equals("33333333333") ||
                cpf.equals("44444444444") ||
                cpf.equals("55555555555") ||
                cpf.equals("66666666666") ||
                cpf.equals("77777777777") ||
                cpf.equals("88888888888") ||
                cpf.equals("99999999999"))
            return false;

        if (cpf.length() != 11) {
            return false;
        }
        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso;
            peso--;
        }
        int digVer1 = (soma * 10) % 11;
        if (digVer1 == 10)
            digVer1 = 0;

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso;
        }
        int digVer2 = (soma * 10) % 11;
        if (digVer2 == 10)
            digVer2 = 0;

        if (Integer.parseInt(cpf.substring(9, 10)) != digVer1 || Integer.parseInt(cpf.substring(10, 11)) != digVer2)
            return false;

        return true;
    }

    public static boolean validarEmail(String email) {

        if (email == null || email.isEmpty())
            return false;

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
