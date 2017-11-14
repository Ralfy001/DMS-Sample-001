package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Foto;

public interface FotoService {

	Foto findById(int id);

	List<Foto> findAll();
	
	List<Foto> findAllByAlbumId(int id);
	
	void saveFoto(Foto foto);
	
	void deleteById(int id);
}
