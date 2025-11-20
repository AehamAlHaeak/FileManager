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
public String name;

 public ClientHandler(Socket socket){
   this.socket=socket;
   

 }

 public void run(){
    try{
      System.out.println("start is ready on port :"+socket.getPort());
    in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out=new PrintWriter(socket.getOutputStream(),true); 
    out.println("enter your name on the network : ");
    String newName=in.readLine();
    System.out.println("name intred");
    name=newName;
    System.out.println(name);
     for ( ClientHandler clint : ServerSysApplication.clients  ) {
      if(clint != this){
       String toSendClient="";
       
       toSendClient=toSendClient+"name : "+clint.name+"\n";
       toSendClient=toSendClient+"port :" + clint.socket.getPort()+"\n";
       System.out.println(toSendClient);
       out.println(toSendClient);
   }
     }
      
    String msg;
    while ((msg = in.readLine()) != null) {
    if (msg.equalsIgnoreCase("exit")) break;
    System.out.println(msg +"sender addr : " +socket.getInetAddress()+" sender port" + socket.getPort());
    if(msg.startsWith("<")){
     int end=msg.indexOf(">");
     String destName=msg.substring(1,end);
     for ( ClientHandler clint : ServerSysApplication.clients  ) {
      if(clint != this){
       if(clint.name.equals(destName)){
         clint.send(msg.substring(end));
         break;
       }
       
   }
     }
    }
    else{
    ServerSysApplication.broadcast(msg, this);
    }
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
