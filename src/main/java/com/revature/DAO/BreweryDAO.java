package com.revature.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Brewery;
import com.revature.util.HibernateUtil;

public class BreweryDAO implements IBreweryDAO {
	private Session s;
	
	//handles the brewery info and the database

	@Override
	public List<Brewery> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Brewery findBrewery(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveBrewery(Brewery b) {
		if (b == null) {
			return false;
		}
		s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Serializable ret = s.save(b);
		if (ret != null ) {
			tx.commit();
			return true;
		} else {
			tx.rollback();
			return false;
		}
	}

	@Override
	public boolean updateBrewery(Brewery b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBrewery(Brewery b) {
		if (b == null) {
			return false;
		}
		s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(b);
		
		tx.commit();
		return true;
	}

}
