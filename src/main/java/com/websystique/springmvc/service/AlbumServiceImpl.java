package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.AlbumDao;
import com.websystique.springmvc.model.Album;


@Service("userService")
@Transactional
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumDao dao;

	public Album findById(int id) {
		return dao.findById(id);
	}

	public Album findByALBUMID(String albumId) {
		Album album = dao.findByALBUMID(albumId);
		return album;
	}

	public void saveAlbum(Album album) {
		dao.save(album);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateAlbum(Album album) {
		Album entity = dao.findById(album.getId());
		if(entity!=null){
			entity.setAlbumId(album.getAlbumId());
			entity.setFirstName(album.getFirstName());
			entity.setFoto(album.getFoto());
		}
	}

	
	public void deleteAlbumByALBUMID(String albumId) {
		dao.deleteByALBUMID(albumId);
	}

	public List<Album> findAllAlbums() {
		return dao.findAllAlbums();
	}

	public boolean isAlbumALBUMIDUnique(Integer id, String albumId) {
		Album album = findByALBUMID(albumId);
		return ( album == null || ((id != null) && (album.getId() == id)));
	}
	
}
