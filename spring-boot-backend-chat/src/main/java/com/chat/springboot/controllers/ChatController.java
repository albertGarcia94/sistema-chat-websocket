package com.chat.springboot.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.chat.springboot.models.documents.Mensaje;
import com.chat.springboot.services.ChatService;

@Controller
public class ChatController {
	@Autowired
	private ChatService chatService;
	
	 @Autowired
	 private SimpMessagingTemplate websocket;
	
	private String[] colores = {"red", "blue", "yellow", "navy", "orange", "green", "aqua"};
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje){
		mensaje.setFecha(new Date().getTime());
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("Nuevo usuario");
		}else {
			chatService.guardarMensaje(mensaje);
		}
		
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String estaEscribiendo(String username) {
		return username.concat("Está escribiendo...");
	}
	
	@MessageMapping("/historial")
	public void historialMensajes(String clienteId) {
		websocket.convertAndSend("/chat/historial/"+clienteId,chatService.obternerUltimos10Mensajes());
	}
}
