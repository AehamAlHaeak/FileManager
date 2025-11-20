package com.FileManager.ClintSys;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;

@SpringBootApplication
public class ClintSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClintSysApplication.class, args);
         Scanner inSc=new Scanner(System.in);
     System.out.println("hellow world the client is works");
      try{
	 Socket socket=new Socket("localhost", 5000);	

	 BufferedReader consolReader=new BufferedReader(new InputStreamReader(System.in));
	 BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	 PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
	 new Thread(() -> {
		try {
			System.out.println("start listining");
		String resp;
		while ((resp = in.readLine()) != null) {
		System.out.println(resp);
		}
		} catch (Exception e) {}
		}).start();
	    String name=inSc.next();
		out.println(name);
		
			    String msg;
				out.println("");
				while (!(msg = consolReader.readLine()).equalsIgnoreCase("exit")) {
					out.println(msg);
					}
				
		socket.close();
	  }
	  catch(Exception e){
		System.out.println(e.getMessage());
	  }





	}

}
