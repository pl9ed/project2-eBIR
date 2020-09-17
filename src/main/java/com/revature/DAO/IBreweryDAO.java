package com.revature.DAO;

import java.util.List;

import com.revature.models.Brewery;

public interface IBreweryDAO {

	public List<Brewery> findAll();
	public Brewery findBrewery(int id);
}
