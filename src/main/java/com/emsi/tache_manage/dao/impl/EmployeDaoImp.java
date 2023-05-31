package com.emsi.tache_manage.dao.impl;

import com.emsi.tache_manage.dao.EmployeDao;
import com.emsi.tache_manage.entities.Employe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeDaoImp implements EmployeDao {
	private Connection conn= DB.getConnection();

	public void insert(Employe employe) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO employe (nom,prenom,email,password) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, employe.getNom());
			ps.setString(2, employe.getPrenom());
			ps.setString(3, employe.getEmail());
			ps.setString(4, employe.getPassword());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);

					employe.setId(id);
				}

				DB.closeResultSet(rs);
			} else {
				System.out.println("Aucune ligne renvoyée");
			}
		} catch (SQLException e) {
			System.err.println("problème d'insertion d'un employe");;
		} finally {
			DB.closeStatement(ps);
		}
	}

	public void update(Employe employe) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE employe SET nom = ? WHERE Id = ?");

			ps.setString(1, employe.getNom());
			ps.setInt(2, employe.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de mise à jour d'un employe");;
		} finally {
			DB.closeStatement(ps);
		}

	}

	public void deleteById(Integer id) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("DELETE FROM employe WHERE id = ?");
			
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("problème de suppression d'un employe");;
		} finally {
			DB.closeStatement(ps);
		}

	}

	
	public Employe findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM employe WHERE id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Employe employe = new Employe();

				employe.setId(rs.getInt("Id"));
				employe.setNom(rs.getString("nom"));
				employe.setPrenom(rs.getString("prenom"));
				employe.setEmail(rs.getString("email"));
				employe.setPassword(rs.getString("password"));

				return employe;
			}

			return null;
		} catch (SQLException e) {
			System.err.println("problème de requête pour trouver un employe");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}

	public List<Employe> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM employe");
			rs = ps.executeQuery();

			List<Employe> listEmploye = new ArrayList<>();

			while (rs.next()) {
				Employe employe = new Employe();

				employe.setId(rs.getInt("Id"));
				employe.setNom(rs.getString("nom"));
				employe.setPrenom(rs.getString("prenom"));
				employe.setEmail(rs.getString("email"));
				employe.setPassword(rs.getString("password"));

				listEmploye.add(employe);
			}

			return listEmploye;
		} catch (SQLException e) {
			System.out.println(e);
			System.err.println("problème de requête pour sélectionner un employe");;
		return null;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}
}
