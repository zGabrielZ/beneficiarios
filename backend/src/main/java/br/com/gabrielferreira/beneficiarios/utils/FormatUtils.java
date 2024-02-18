package br.com.gabrielferreira.beneficiarios.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtils {

    private FormatUtils(){}

    public static boolean validarFormato(String valorPattern, String valor){
        Pattern pattern = Pattern.compile(valorPattern);
        Matcher matcher = pattern.matcher(valor);
        return matcher.matches();
    }
}
