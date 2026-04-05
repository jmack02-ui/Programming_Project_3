package team1;
//=============================================================================
//File:             Result.java
//Team Name:        Team 1
//Team Members:     Keanu Cruz, Johnathan McElprang, Michael McCleary , Pong Vodmongkol
//Course/Section:   CS 2430
//Project:          Programming Project 3 – Optimal selection(Spring 2026)
//Primary Author:   Implementation Lead (Johnathan)
//=============================================================================

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores our outcomes. 
 * 
 */
public class Result {

    private List<Experiment> chosenItems;
    private int totalWeight;
    private int totalRating;
    
    /**
     * Constructor for Result object. 
     * 
     */
    public Result() {
        chosenItems = new ArrayList<>();
        totalWeight = 0;
        totalRating = 0;
    }
    
    /**
     * Adds an experiment to the result.
     * 
     * @param e
     */
    public void addItem(Experiment e) {
        chosenItems.add(e);
    }

    /**
     * Method to reset totals iterate through items and accumulate totals. 
     * 
     */
    public void calculateTotals() {
        totalWeight = 0;
        totalRating = 0;

        for (Experiment e : chosenItems) {
            totalWeight += e.getWeight();
            totalRating += e.getRating();
        }
    }

    /**
     * Getter for total weight
     * 
     * @return	totalWeight
     */
    public int getTotalWeight() {
        return totalWeight;
    }

    /**
     * Getter for total rating
     * 
     * @return	totalRating
     */
    public int getTotalRating() {
        return totalRating;
    }

    /**
     * Getter for chosen experiments
     * 
     * @return	chosenItems
     */
    public List<Experiment> getChosenItems() {
        return chosenItems;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Experiment e : chosenItems) {
            sb.append(e).append("\n");
        }

        sb.append("Total Weight: ").append(totalWeight).append("\n");
        sb.append("Total Rating: ").append(totalRating).append("\n");

        return sb.toString();
    }
}
