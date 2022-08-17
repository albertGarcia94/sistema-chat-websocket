package com.chat.springboot.services;

import java.util.List;

import com.chat.springboot.models.documents.Mensaje;

public interface ChatService {
	public List<Mensaje> obternerUltimos10Mensajes();
	public Mensaje guardarMensaje(Mensaje mensaje);
}
