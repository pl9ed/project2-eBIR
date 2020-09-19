package com.revature.DAO;

import java.util.List;

import com.revature.models.Brewery;

public interface IBreweryDAO {

	public List<Brewery> findAll();
	
	public Brewery findBrewery(int id);
	
	public boolean saveBrewery(Brewery b);
	public boolean updateBrewery(Brewery b);
	public boolean deleteBrewery(Brewery b);
}
