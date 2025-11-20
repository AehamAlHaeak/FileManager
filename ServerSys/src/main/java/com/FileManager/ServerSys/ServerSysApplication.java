package com.FileManager.ServerSys;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

import com.FileManager.ServerSys.models.ClientHandler;

@SpringBootApplication
public class ServerSysApplication {
	private static final int PORT = 5000;
	public static Set<ClientHandler> clients =Collections.synchronizedSet(new HashSet<>());
	public static void main(String[] args) {
		SpringApplication.run(ServerSysApplication.class, args);
		ServerSocket servSoc;
  try{
		servSoc=new ServerSocket(PORT);

		while(true){
			Socket clientsock=servSoc.accept();
			
			ClientHandler client=new ClientHandler(clientsock);
			clients.add(client);
			client.start();

		}
  }
  catch(Exception e){
    System.out.println(e.getMessage());
  }
         		
	}
	

	public static void broadcast(String msg,ClientHandler sender){
     
     for (ClientHandler clint : clients) {
		if(clint != sender){
         clint.send(msg);
		}

	 }

	}

}
