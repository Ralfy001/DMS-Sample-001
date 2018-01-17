package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Album;


@Repository("albumDao")
public class AlbumDaoImpl extends AbstractDao<Integer, Album> implements AlbumDao {

	public Album findById(int id) {
		Album album = getByKey(id);
		return album;
	}

	public Album findByALBUMID(String albumId) {
		System.out.println("ALBUMID : "+albumId);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("albumId", albumId));
		Album album = (Album)crit.uniqueResult();
		return album;
	}

	@SuppressWarnings("unchecked")
	public List<Album> findAllAlbums() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Album> albums = (List<Album>) criteria.list();
		
		return albums;
	}

	public void save(Album album) {
		persist(album);
	}

	public void deleteByALBUMID(String albumId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("albumId", albumId));
		Album album = (Album)crit.uniqueResult();
		delete(album);
	}

}
