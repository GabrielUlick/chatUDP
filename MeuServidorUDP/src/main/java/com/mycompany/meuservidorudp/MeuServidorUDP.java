/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.meuservidorudp;

import java.net.*;
import java.io.*;

/**
 *
 * @author 2020122760307
 */
public class MeuServidorUDP {

    private static BaseDeDados bd = null;

    public static void main(String[] args) {
        DatagramSocket aSocket = null;
        bd = new BaseDeDados();

        try {
            aSocket = new DatagramSocket(6789);

            while (true) {
                //==== RECEBIMENTO DE MENSAGEM DO CLIENTE =====================================

                byte[] buffer = new byte[600];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                //==== PROCESSAMENTO DO SERVIDOR SOBRE A MENSAGEM RECEBIDA ====================
                String mensagem = new String(request.getData());

                bd.insere(mensagem.toUpperCase());
                String resposta = bd.le();
                byte[] todasMsg = resposta.getBytes();

                //==== ENVIO DE MENSAGEM DE RESPOSTA ==========================================
                DatagramPacket reply = new DatagramPacket(todasMsg, todasMsg.length, request.getAddress(), request.getPort());

                aSocket.send(reply);
            }//fim do While
        } catch (SocketException e) {
            System.out.println("Servidor - Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Servidor - Input Output: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }//try catch      
    }//main
}//classe
