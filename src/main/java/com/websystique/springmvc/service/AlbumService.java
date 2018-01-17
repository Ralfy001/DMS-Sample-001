package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Album;


public interface AlbumService {
	
	Album findById(int id);
	
	Album findByALBUMID(String albumId);
	
	void saveAlbum(Album album);
	
	void updateAlbum(Album album);
	
	void deleteAlbumByALBUMID(String albumId);

	List<Album> findAllAlbums();
	
	boolean isAlbumALBUMIDUnique(Integer id, String albumID);

}