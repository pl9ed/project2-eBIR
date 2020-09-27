package com.revature.DAO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.OptimisticLockException;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Review;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

@Repository
public class ReviewDAO implements IReviewDAO {
	private static Logger log = Logger.getLogger(ReviewDAO.class);

	private Session s;

	public ReviewDAO() {
		super();
	}

	@Override
	public Set<Review> findAll() {
		s = HibernateUtil.getSession();
		return s.createQuery("FROM Review r", Review.class).getResultStream().collect(Collectors.toSet()); //.getResultList();
	}

	@Override
	public Set<Review> findByBrewery(int b) {
		s = HibernateUtil.getSession();

		Set<Review> ret = new HashSet<>();
		if (b > 0) {
//			Transaction tx = s.beginTransaction();
//			tx.begin();
			Query<Review> q = s.createQuery("FROM Review r WHERE r.brewery = :id", Review.class);
			q.setParameter("id", b);
			ret = q.getResultStream().collect(Collectors.toSet());
		}

		return ret;
	}

	@Override
	public Set<Review> findByUser(String username) {
		s = HibernateUtil.getSession();
		Set<Review> ret = new HashSet<>();
		if (username != null) {
			Query<Review> q = s.createQuery("FROM Review r WHERE r.submitter.username = :username", Review.class);
			q.setParameter("username", username);
			ret = q.getResultStream().collect(Collectors.toSet());
		}
		return ret;
	}

	@Override
	public Set<Review> findByUser(User u) {
		if (u != null) {
			return findByUser(u.getUsername());
		}
		return new HashSet<Review>();
	}

	@Override
	public boolean saveReview(Review review) {
//		if (review == null) {
//			return false;
//		}
		HibernateUtil.closeSession();
		s = HibernateUtil.getSession();
		Transaction t = s.beginTransaction();

		try {
			s.save(review);
			t.commit();
			return true;
		} catch (NonUniqueObjectException e) {
			log.trace(e, e);
			//System.out.println("Nonunique adding: " + review);
		} catch (Exception e) {
			log.trace(e, e);
		}
		t.rollback();
		return false;

	}

	@Override
	public Review find(int id) {
		s = HibernateUtil.getSession();
		return s.get(Review.class, id);
	}

	@Override
	public boolean updateReview(Review review) {
		if (!(review instanceof Review) || !(review.getBrewery() > 0)) {
			return false;
		}
		try {
			Session s = HibernateUtil.getSession();
			//s.clear();
			Transaction tx = s.beginTransaction();

			Review ret = (Review) s.merge(review);
			if (ret.equals(review)) {
				tx.commit();
				return true;
			} else {
				tx.rollback();
				return false;
			}
		} catch (Exception e) {
			log.trace(e, e);
			return false;
		}
	}

	@Override
	public boolean deleteReview(Review review) {
		if (review == null) {
			return false;
		}
		s = HibernateUtil.getSession();
		//s.clear();
		Transaction tx = s.beginTransaction();
		try {
			s.delete(review);
			tx.commit();
		} catch (OptimisticLockException e) {
			// this exception gets thrown when we try to delete a nonexistent entity
			// can just ignore
		} catch (Exception e) {
			log.trace(e, e);
			return false;
		}
		return true;
	}

}
