package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.FotoDao;
import com.websystique.springmvc.model.Foto;

@Service("fotoService")
@Transactional
public class FotoServiceImpl implements FotoService {

	@Autowired
	FotoDao dao;

	public Foto findById(int id) {
		return dao.findById(id);
	}

	public List<Foto> findAll() {
		return dao.findAll();
	}

	public List<Foto> findAllByAlbumId(int albumId) {
		return dao.findAllByAlbumId(albumId);
	}
	
	public void saveFoto(Foto foto){
		dao.save(foto);
	}

	public void deleteById(int id){
		dao.deleteById(id);
	}
	
}
