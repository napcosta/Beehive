package com.example.bee.beehive;

import java.text.SimpleDateFormat;

/**
 * Created by Nuno on 30/06/2015.
 */
public class Action extends dbListEntry {

	int hive_id;
	int apiary_id;
	String name;
	int day;
	int month;
	int year;

	public Action(){}
	public Action(String name, int day, int month, int year, int apiary_id, int hive_id)
	{
		this.name = name;
		this.hive_id = hive_id;
		this.apiary_id = apiary_id;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public Action(int id, String name, int apiary_id, int hive_id)
	{
		this.name = name;
		this.hive_id = apiary_id;
		this.apiary_id = apiary_id;
		this.id = id;
	}

	public int getID()
	{
		return id;
	}

	public int getHiveID()
	{
		return hive_id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return String.valueOf(name);
	}

}