package com.FileManager.ServerSys.models;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.net.Socket;

import com.FileManager.ServerSys.ServerSysApplication;

public class ClientHandler extends Thread{

 Socket socket;
 PrintWriter out;
 BufferedReader in;

 public ClientHandler(Socket socket){
   this.socket=socket;

 }

 public void run(){
    try{
    in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out=new PrintWriter(socket.getOutputStream(),true); 

    String msg;
    while ((msg = in.readLine()) != null) {
    if (msg.equalsIgnoreCase("exit")) break;
    System.out.println(msg +"sender addr : " +socket.getInetAddress()+" sender port" + socket.getPort());
    ServerSysApplication.broadcast(msg, this);
    }

   

    }
    catch(Exception e){
     
    }
    finally{
        try{
            socket.close();
        }catch(Exception e){
          
        }
        
    }
 }


 public void send(String msg){
   

    out.println(msg);
    

 }



}
