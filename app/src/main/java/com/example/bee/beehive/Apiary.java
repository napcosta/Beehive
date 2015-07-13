package com.example.bee.beehive;

/**
 * Created by Nuno on 30/06/2015.
 */
public class Apiary {

    int id;
    String name;

    public Apiary(){}
    public Apiary(String name)
    {
        this.name = name;
    }

    public Apiary(int id, String name)
    {
        this.name = name;
        this.id = id;
    }

    public int getID()
    {
        return id;
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
