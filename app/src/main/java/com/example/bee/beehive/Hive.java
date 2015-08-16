package com.example.bee.beehive;

/**
 * Created by Nuno on 30/06/2015.
 */
public class Hive extends dbListEntry {

	int id;
	int apiary_id;
	String name;
	String honeycomb_count;
	String breedingcomb_count;

	public Hive(){}
	public Hive(String name, String honeycomb_count, String breedingcomb_count, int apiary_id)
	{
		this.name = name;
		this.apiary_id = apiary_id;
		this.honeycomb_count = honeycomb_count;
		this.breedingcomb_count = breedingcomb_count;
	}

	public Hive(int id, String name, int apiary_id)
	{
		this.name = name;
		this.apiary_id = apiary_id;
		this.id = id;
	}

	public Hive(int id, String name, String honeycomb_count, String breedingcomb_count, int apiary_id)
	{
		this.id = id;
		this.name = name;
		this.honeycomb_count = honeycomb_count;
		this.breedingcomb_count = breedingcomb_count;
	}

	public int getID()
	{
		return id;
	}

	public int getApiaryID()
	{
		return apiary_id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return String.valueOf(name);
	}

	public String getHoneycombCount()
	{
		return String.valueOf(honeycomb_count);
	}

	public String getBreedingcombCount()
	{
		return String.valueOf(breedingcomb_count);
	}


}
