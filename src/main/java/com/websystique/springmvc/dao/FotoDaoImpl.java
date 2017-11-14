package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Foto;

@Repository("fotoDao")
public class FotoDaoImpl extends AbstractDao<Integer, Foto> implements FotoDao {

  @SuppressWarnings("unchecked")
  public List<Foto> findAll() {
    Criteria crit = createEntityCriteria();
    return (List<Foto>) crit.list();
  }

  public void save(Foto foto) {
    persist(foto);
  }


  public Foto findById(int id) {
    return getByKey(id);
  }

  @SuppressWarnings("unchecked")
  public List<Foto> findAllByAlbumId(int albumId) {
    Criteria crit = createEntityCriteria();
    Criteria userCriteria = crit.createCriteria("album");
    userCriteria.add(Restrictions.eq("id", albumId));
    return (List<Foto>) crit.list();
  }


  public void deleteById(int id) {
    Foto document = getByKey(id);
    delete(document);
  }
}
