package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Album;


public interface AlbumService {
	
	Album findById(int id);
	
	Album findBySSO(String sso);
	
	void saveAlbum(Album album);
	
	void updateAlbum(Album album);
	
	void deleteAlbumBySSO(String sso);

	List<Album> findAllAlbums();
	
	boolean isAlbumSSOUnique(Integer id, String sso);

}