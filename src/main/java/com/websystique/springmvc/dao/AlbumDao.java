package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Album;


public interface AlbumDao {

	Album findById(int id);
	
	Album findBySSO(String sso);
	
	void save(Album album);
	
	void deleteBySSO(String sso);
	
	List<Album> findAllAlbums();

}

