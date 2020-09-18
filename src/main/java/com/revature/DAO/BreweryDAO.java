package com.revature.DAO;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.models.Brewery;
import com.revature.util.HibernateUtil;

@Repository
public class BreweryDAO implements IBreweryDAO {
	private Session s;
	private static Logger log = Logger.getLogger(BreweryDAO.class);
	
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
			log.info("saved " + b.getName() + " into database.");
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
		log.info("brewery deleted");
		return true;
	}

}
