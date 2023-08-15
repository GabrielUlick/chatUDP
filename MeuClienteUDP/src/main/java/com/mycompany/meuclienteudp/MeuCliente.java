/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.meuclienteudp;

import java.io.IOException;
    import java.net.*;
    import java.net.*;
/**
 *
 * @author 2020122760307
 */
public class MeuCliente {
    private String nomeDNS;
    private int serverPort;
    private byte[] meuIP;
    
    public MeuCliente(){
        //método construtor
        
        try{
            InetAddress endereco = InetAddress.getLocalHost();
            
            nomeDNS = endereco.getHostName();
            
            meuIP = endereco.getAddress();
        
        } catch (UnknownHostException e) {
            System.out.println("Host Desconhecido: " + e.getMessage());
        }
        
        serverPort = 6789;
    }   
    
    public String enviaMensagem(String mensagem){
        DatagramSocket aSocket = null;
        String resposta = new String("");
        
        try {
            aSocket = new DatagramSocket();
            byte [ ]m = mensagem.getBytes();
            InetAddress aHost = InetAddress.getByName(nomeDNS);
            
            DatagramPacket request = new DatagramPacket (m, m.length, aHost,serverPort);
            
            aSocket.send(request);
            
            byte[ ] buffer = new byte [600];
            
            DatagramPacket reply = new DatagramPacket (buffer, buffer.length);
            
            aSocket.receive(reply);
            
            resposta = new String(reply.getData());
            
            resposta = resposta + "\n";
            
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Input Output: " + e.getMessage());
        } finally {
            if(aSocket != null)
                aSocket.close();
        }
        return resposta;
    }
    
    public MeuCliente(String nomeDNSServidor){
        //Método construtor para quando SERVIDOR e CLIENTE rodarem em
        //máquinas diferentes 
        nomeDNS = "col-8943-li01";
        
        meuIP = null;
        serverPort = 6789;
    }
    
    public String getNomeDNS(){
        return nomeDNS;
    }
    
    public int getServerPort(){
        return serverPort;
    }
    public String getMeuIP(){
        String s = new String(meuIP);
        return s;
    }
}
