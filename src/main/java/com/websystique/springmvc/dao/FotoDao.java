package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Foto;

public interface FotoDao {

	List<Foto> findAll();
	
	Foto findById(int id);
	
	void save(Foto foto);
	
	List<Foto> findAllByAlbumId(int albumId);
	
	void deleteById(int id);
}
