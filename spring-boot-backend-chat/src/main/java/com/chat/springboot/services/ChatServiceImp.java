package com.chat.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.springboot.Dao.ChatRepository;
import com.chat.springboot.models.documents.Mensaje;

@Service
public class ChatServiceImp implements ChatService{
	@Autowired
	private ChatRepository chatDao;
	
	@Override
	public List<Mensaje> obternerUltimos10Mensajes() {
		return chatDao.findFirt10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardarMensaje(Mensaje mensaje) {
		return chatDao.save(mensaje);
	}

}
