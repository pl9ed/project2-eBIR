package com.revature.DAO;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.TestData;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserDAOTest {
	private TestData td = new TestData();
	private UserDAO ud;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ud = new UserDAO();
	}

	@After
	public void tearDown() throws Exception {
		HibernateUtil.closeSession();
		clearDB();
	}
	
	private void clearDB() {
		String hql = "delete from User";
		try {
			Session s = HibernateUtil.getSession();
			Transaction tx = s.beginTransaction();
			Query query = s.createQuery(hql);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSaveUser() {
		assertTrue(ud.saveUser(td.u1));
	}
	
	@Test
	public void testSaveUserDuplicate() {
		assertTrue(ud.saveUser(td.u1));
		int n = ud.findAll().size();
		ud.saveUser(td.u1);
		assertTrue(n == ud.findAll().size());
	}
	
	@Test
	public void testDeleteUser() {
		ud.saveUser(td.u1);
		assertTrue(ud.deleteUser(td.u1));
		assertTrue(ud.findUser(td.u1.getUsername()) == null);
	}
	
	@Test
	public void testSaveUserNull() {
		assertFalse(ud.saveUser(null));
	}
	
	@Test
	public void testSaveUserMissingReq() {
		User u = new User();
		assertFalse(ud.saveUser(u));
	}

}
