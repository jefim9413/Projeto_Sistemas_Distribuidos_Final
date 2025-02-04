package com.distribuidos.server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

public class Servente {
    public String calcularSoma(String json) {
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray numeros = jsonObject.getAsJsonArray("numeros");

        int soma = 0;
        for (int i = 0; i < numeros.size(); i++) {
            soma += numeros.get(i).getAsInt();
        }

        return "Resultado da soma: " + soma;
    }

    public String calcularMedia(String json) {
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray numeros = jsonObject.getAsJsonArray("numeros");

        if (numeros.size() == 0) {
            return "Erro: Lista vazia, não é possível calcular a média.";
        }

        int soma = 0;
        for (int i = 0; i < numeros.size(); i++) {
            soma += numeros.get(i).getAsInt();
        }

        double media = (double) soma / numeros.size();
        return "Resultado da média: " + media;
    }

    public String resolverEquacaoSegundoGrau(String json) {
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        int a = jsonObject.get("a").getAsInt();
        int b = jsonObject.get("b").getAsInt();
        int c = jsonObject.get("c").getAsInt();

        double delta = Math.pow(b, 2) - (4 * a * c);

        if (delta < 0) {
            return "Erro: Equação não possui raízes reais.";
        }

        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
        double x2 = (-b - Math.sqrt(delta)) / (2 * a);

        return "Raízes da equação: x1=" + x1 + ", x2=" + x2;
    }
}
