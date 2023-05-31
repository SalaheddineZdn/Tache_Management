package com.emsi.tache_manage.dao.impl;

import com.emsi.tache_manage.dao.TacheDao;
import com.emsi.tache_manage.entities.Employe;
import com.emsi.tache_manage.entities.Tache;
import com.emsi.tache_manage.enums.Priority;
import com.emsi.tache_manage.enums.Statut;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TacheDaoImp implements TacheDao {
	private Connection conn= DB.getConnection();

	public void insert(Tache tache) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO tache (nom, description, statut, priority) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, tache.getNom());
			ps.setString(2, tache.getDescription());
			ps.setString(3, String.valueOf(tache.getStatut()));
			ps.setString(4, String.valueOf(tache.getPriority()));

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);

					tache.setId(id);
				}

				DB.closeResultSet(rs);
			} else {
				System.out.println("Aucune ligne renvoyée");
			}
		} catch (SQLException e) {
			System.err.println("problème d'insertion d'une tache");;
			e.printStackTrace();
		} finally {
			DB.closeStatement(ps);
		}
	}

	public void update(Tache tache) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE tache SET nom = ?, description = ?, statut = ?, priority = ? WHERE id = ?");

			ps.setString(1, tache.getNom());
			ps.setString(2, tache.getDescription());
			ps.setString(3, tache.getStatut().name());
			ps.setString(4, tache.getPriority().name());
			ps.setInt(5, tache.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de mise à jour d'une tache");;
		} finally {
			DB.closeStatement(ps);
		}

	}

	public void deleteById(Integer id) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM tache WHERE id = ?");
			
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de suppression d'une tache");;
		} finally {
			DB.closeStatement(ps);
		}

	}

	
	public Tache findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM tache WHERE id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Tache tache = new Tache();

				tache.setId(rs.getInt("Id"));
				tache.setNom(rs.getString("nom"));
				tache.setDescription(rs.getString("description"));
				tache.setStatut(Statut.valueOf(rs.getString("statut")));
				tache.setPriority(Priority.valueOf(rs.getString("priority")));

				return tache;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver une tache");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}

	public List<Tache> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM tache");
			rs = ps.executeQuery();

			List<Tache> listTache = new ArrayList<>();

			while (rs.next()) {
				Tache tache = new Tache();

				tache.setId(rs.getInt("id"));
				tache.setNom(rs.getString("nom"));
				tache.setDescription(rs.getString("description"));
				tache.setStatut(Statut.valueOf(rs.getString("statut")));
				tache.setPriority(Priority.valueOf(rs.getString("priority")));

				listTache.add(tache);
			}

			return listTache;
		} catch (SQLException e) {
			System.out.println(e);
			System.err.println("problème de requête pour sélectionner une tache");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}
}
