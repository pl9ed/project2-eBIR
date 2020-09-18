package com.revature.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.TestUtilities;
import com.revature.models.Brewery;
import com.revature.util.HibernateUtil;

public class BreweryDAOTest {
	
	private TestUtilities td = new TestUtilities();
	private BreweryDAO bd;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtilities.clearDB();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bd = new BreweryDAO();
	}

	@After
	public void tearDown() throws Exception {
		HibernateUtil.closeSession();
		TestUtilities.clearDB();

	}

	@Test
	public void testSaveBrewery() {
		assertTrue(bd.saveBrewery(td.b1));
	}
	
	@Test
	public void testSaveBreweryNull() {
		assertFalse(bd.saveBrewery(null));
	}
	
	@Test
	public void testSaveBreweryDuplicate() {
		assertTrue(bd.saveBrewery(td.b1));
		int n = bd.findAll().size();
		bd.saveBrewery(td.b1);
		
		// check number of records is the same as before
		assertTrue(n == bd.findAll().size());
	}
	
	
	// might not be necessary since id is an int
//	@Test
//	public void testSaveBreweryMissingReq() {
//		
//		
//	}
	
	@Test
	public void testDeleteBrewery() {
		bd.saveBrewery(td.b1);
		assertTrue(bd.deleteBrewery(td.b1));
	}
	
	@Test
	public void testDeleteNonExistant() {
		bd.saveBrewery(td.b1);
		assertTrue(bd.deleteBrewery(td.b2));
		assertTrue(1 == bd.findAll().size());
	}
	
	@Test
	public void testFindBrewery() {
		bd.saveBrewery(td.b1);
		
		assertEquals(td.b1, bd.findBrewery(td.b1.getId()));
	}
	
	@Test
	public void testFindBreweryNonExistent() {
		assertTrue(null == bd.findBrewery(-1));
	}
	
	@Test
	public void testUpdateBrewery() {
		bd.saveBrewery(td.b1);
		Brewery temp = td.b1;
		String updatedName = "test name";
		temp.setName(updatedName);
		assertTrue(bd.updateBrewery(temp));
		assertEquals(bd.findBrewery(td.b1.getId()).getName(), updatedName);
	}
	
	@Test
	public void testUpdateNonExistent() {
		bd.updateBrewery(td.b1);
		assertEquals(bd.findBrewery(td.b1.getId()), td.b1);
	}
	
	@Test
	public void testUpdateBreweryNull() {
		assertFalse(bd.updateBrewery(null));
	}

}
