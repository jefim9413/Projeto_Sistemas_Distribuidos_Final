package com.distribuidos.server;

import com.distribuidos.model.Mensagem;
import com.google.gson.Gson;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class ServidorUDP {
    private static final int PORT = 9876;
    private static final long TEMPO_EXPIRACAO = 5000; 
    private static Map<String, Long> historicoRequisicoes = new HashMap<>();

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println(GREEN + " Servidor UDP iniciado na porta " + PORT + RESET);
            System.out.println(YELLOW + " Aguardando requisições...\n" + RESET);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                System.out.println(BLUE + " Aguardando mensagem do cliente..." + RESET);
                serverSocket.receive(receivePacket);

                String receivedJson = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(GREEN + " Mensagem recebida do cliente: " + RESET + receivedJson);

                Gson gson = new Gson();
                Mensagem mensagem = gson.fromJson(receivedJson, Mensagem.class);

                historicoRequisicoes.entrySet().removeIf(entry -> System.currentTimeMillis() - entry.getValue() > TEMPO_EXPIRACAO);

                if (historicoRequisicoes.containsKey(mensagem.getRequestID())) {
                    System.out.println(RED + " Requisição duplicada detectada, ignorando..." + RESET);
                    continue;
                }

                historicoRequisicoes.put(mensagem.getRequestID(), System.currentTimeMillis());

                String resposta = Despachante.processarRequisicao(mensagem);
                System.out.println(YELLOW + " Resposta gerada: " + RESET + resposta);

                byte[] sendData = resposta.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                        receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
                System.out.println(GREEN + " Resposta enviada com sucesso!\n" + RESET);
            }
        } catch (Exception e) {
            System.out.println(RED + " Erro no servidor: " + e.getMessage() + RESET);
            e.printStackTrace();
        }
    }
}
