package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Album;


public interface AlbumDao {

	Album findById(int id);
	
	Album findByALBUMID(String albumId);
	
	void save(Album album);
	
	void deleteByALBUMID(String albumId);
	
	List<Album> findAllAlbums();

}

