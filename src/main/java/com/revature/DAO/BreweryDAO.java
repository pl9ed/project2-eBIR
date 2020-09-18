package com.revature.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.models.Brewery;
import com.revature.util.HibernateUtil;

@Repository
public class BreweryDAO implements IBreweryDAO {
	private Session s;
	
	{
		s = HibernateUtil.getSession();
	}
	//handles the brewery info and the database

	@Override
	public List<Brewery> findAll() {		
		return s.createQuery("FROM Brewery brews", Brewery.class)
				.getResultList();
	}

	@Override
	public Brewery findBrewery(int id) {
		return s.find(Brewery.class, id);
	}

	@Override
	public boolean saveBrewery(Brewery b) {
		if (b == null) {
			return false;
		}
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
		if (!(b instanceof Brewery)) {
			return false;
		}
		
		Transaction tx = s.beginTransaction();
		Brewery temp = (Brewery) s.merge(b);
		if (temp.equals(b)) {
			tx.commit();
			return true;
		}
		tx.rollback();
		return false;
	}

	@Override
	public boolean deleteBrewery(Brewery b) {
		if (b == null) {
			return false;
		}

		Transaction tx = s.beginTransaction();
		s.delete(b);
		
		tx.commit();
		return true;
	}

}
