package com.distribuidos.client;

import com.google.gson.Gson;
import com.distribuidos.model.Mensagem;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
    private static final int SERVER_PORT = 9876;
    private static final int TIMEOUT = 5000;

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String RED = "\u001B[31m";

    public static String doOperation(String objectReference, String methodID, String arguments) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");

            String uniqueRequestID = "req" + System.currentTimeMillis();
            Mensagem mensagem = new Mensagem(objectReference, methodID, arguments, uniqueRequestID);

            Gson gson = new Gson();
            String jsonMensagem = gson.toJson(mensagem);

            System.out.println(YELLOW + " Aguardando resposta..." + RESET);

            byte[] sendData = jsonMensagem.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            socket.send(sendPacket);
            System.out.println(GREEN + " Mensagem enviada!" + RESET);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String resposta = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(GREEN + " Resposta do servidor: " + RESET + resposta);
            return resposta;
        } catch (Exception e) {
            System.out.println(RED + " Erro ao receber resposta: " + e.getMessage() + RESET);
            return "Erro: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println(YELLOW + " Enviando requisições para o servidor...\n" + RESET);

        String somaJson = "{\"numeros\": [1, 2, 30]}";
        String resultadoSoma = doOperation("CalculadoraService", "calcularSoma", somaJson);

        String mediaJson = "{\"numeros\": [40, 40, 40, 40]}";
        String resultadoMedia = doOperation("CalculadoraService", "calcularMedia", mediaJson);

        String equacaoJson = "{\"a\": 1, \"b\": -3, \"c\": 2}";
        String resultadoEquacao = doOperation("CalculadoraService", "resolverEquacaoSegundoGrau", equacaoJson);

        System.out.println(GREEN + "\nTestes concluídos com sucesso!" + RESET);
    }
}
