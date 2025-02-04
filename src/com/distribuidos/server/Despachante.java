package com.distribuidos.server;

import com.distribuidos.model.Mensagem;
import com.google.gson.Gson;
import java.lang.reflect.Method;

public class Despachante {
    public static String processarRequisicao(Mensagem mensagem) {
        try {
            Object servico = new Servente();
            Method metodo = servico.getClass().getMethod(mensagem.getMethodID(), String.class);

            return (String) metodo.invoke(servico, mensagem.getArguments());
        } catch (Exception e) {
            return "Erro no despachante: " + e.getMessage();
        }
    }
}
