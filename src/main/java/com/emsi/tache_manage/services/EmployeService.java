package com.emsi.tache_manage.services;

import com.emsi.tache_manage.dao.EmployeDao;
import com.emsi.tache_manage.dao.impl.EmployeDaoImp;
import com.emsi.tache_manage.entities.Employe;

import java.util.List;

public class EmployeService {
	private EmployeDao employeDao = new EmployeDaoImp();

	public List<Employe> findAll() {
		return employeDao.findAll();
	}

	public Employe findById(Integer id) {
		return employeDao.findById(id);
	}

	public void save(Employe employe) {
		
			employeDao.insert(employe);
		
	}
	public void update(Employe employe) {
		
			employeDao.update(employe);
		
	}
	public void remove(Employe employe) {
		employeDao.deleteById(employe.getId());
	}
}
