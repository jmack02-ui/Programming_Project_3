package team1;
//=============================================================================
//File:             Experiment.java
//Team Name:        Team 1
//Team Members:     Keanu Cruz, Johnathan McElprang, Michael McCleary , Pong Vodmongkol
//Course/Section:   CS 2430
//Project:          Programming Project 3 – Optimal selection(Spring 2026)
//Primary Author:   Implementation Lead (Johnathan)
//=============================================================================

/**
 * Constructor class for Experiments
 * 
 */
public class Experiment {

    private int id;
    private String name;
    private int weight;
    private int rating;

    /**
     * Constructor for Experiment Object
     * 
     * @param id
     * @param name
     * @param weight
     * @param rating
     */
    public Experiment(int id, String name, int weight, int rating) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.rating = rating;
    }

    /**
     * Getter for experiment Id
     * 
     * @return	id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for experiment name
     * 
     * @return	name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for experiment weight
     * 
     * @return	weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Getter for rating
     * 
     * @return
     */
    public int getRating() {
        return rating;
    }

    /**
     * Getter for experiment ratio
     * 
     * @return	ratio
     */
    public double getRatio() {
        return (double) rating / weight;
    }

    @Override
    public String toString() {
        return String.format("%d - %s (Weight: %d, Rating: %d)",
                id, name, weight, rating);
    }
}
