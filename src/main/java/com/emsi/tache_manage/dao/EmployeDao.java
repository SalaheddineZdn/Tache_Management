package com.emsi.tache_manage.dao;

import com.emsi.tache_manage.entities.Employe;

import java.util.List;

public interface EmployeDao {
	void insert(Employe employe);

	void update(Employe employe);

	void deleteById(Integer id);

	Employe findById(Integer id);

	List<Employe> findAll();

}
