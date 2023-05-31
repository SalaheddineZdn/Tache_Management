package com.emsi.tache_manage.services;

import com.emsi.tache_manage.dao.TacheDao;
import com.emsi.tache_manage.dao.impl.TacheDaoImp;
import com.emsi.tache_manage.entities.Tache;

import java.util.List;

public class TacheService {
	private TacheDao tacheDao = new TacheDaoImp();

	public List<Tache> findAll() {
		return tacheDao.findAll();
	}

	public Tache findById(Integer id) {
		return tacheDao.findById(id);
	}

	public void save(Tache tache) {
		
			tacheDao.insert(tache);
		
	}
	public void update(Tache tache) {
		
			tacheDao.update(tache);
		
	}
	public void remove(Tache tache) {
		tacheDao.deleteById(tache.getId());
	}
}
