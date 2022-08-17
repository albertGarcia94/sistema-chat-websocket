package com.chat.springboot.Dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chat.springboot.models.documents.Mensaje;

public interface ChatRepository extends MongoRepository<Mensaje, String>{
	public List<Mensaje> findFirt10ByOrderByFechaDesc();
}
