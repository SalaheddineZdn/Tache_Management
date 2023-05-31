package com.emsi.tache_manage.dao;

import com.emsi.tache_manage.entities.Tache;

import java.util.List;

public interface TacheDao {
	void insert(Tache tache);

	void update(Tache tache);

	void deleteById(Integer id);

	Tache findById(Integer id);

	List<Tache> findAll();
}
