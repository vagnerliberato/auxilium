package br.com.softland.baseConhecimento.global;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Funcoes {

    public static String geraCampo(String usuario) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return usuario + " - " + (format.format(new Date()));
    }
}
