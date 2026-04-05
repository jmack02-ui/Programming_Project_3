package team1;
//=============================================================================
//File:             KnapsackSolver.java
//Team Name:        Team 1
//Team Members:     Keanu Cruz, Johnathan McElprang, Michael McCleary , Pong Vodmongkol
//Course/Section:   CS 2430
//Project:          Programming Project 3 – Optimal selection(Spring 2026)
//Primary Author:   Implementation Lead (Johnathan)
//=============================================================================

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class to solve problem using greedy methods and brute force. 
 * 
 */
public class KnapsackSolver {

    private List<Experiment> items;
    private int maxWeight;

    /**
     * Constructor initializes solver with the experiments and the max weight.
     * 
     * @param items
     * @param maxWeight
     */
    public KnapsackSolver(List<Experiment> items, int maxWeight) {
        this.items = items;
        this.maxWeight = maxWeight;
    }

    /**
     * Method to sort items from highest to lowest rating adds them in order until it hits max weight.
     * 
     * @return
     */
    public Result greedyByRating() {

        List<Experiment> sorted = new ArrayList<>(items);
        sorted.sort((a, b) -> b.getRating() - a.getRating());

        return buildGreedyResult(sorted);
    }

    /**
     * Method to sort items from lightest to heaviest.
     * 
     * @return
     */
    public Result greedyByWeight() {

        List<Experiment> sorted = new ArrayList<>(items);
        sorted.sort(Comparator.comparingInt(Experiment::getWeight));

        return buildGreedyResult(sorted);
    }

    /**
     * Method to sort items by rating to weight ratio. 
     * 
     * @return
     */
    public Result greedyByRatio() {

        List<Experiment> sorted = new ArrayList<>(items);
        sorted.sort((a, b) -> Double.compare(b.getRatio(), a.getRatio()));

        return buildGreedyResult(sorted);
    }

    /**
     * Helper method to take a sorted list and add items if they fit
     * it tracks current weight to make sure we dont go over max weight
     * and updates the totals at the end
     * 
     * @param sorted
     * @return
     */
    private Result buildGreedyResult(List<Experiment> sorted) {

        Result result = new Result();
        int currentWeight = 0;

        for (Experiment e : sorted) {
            if (currentWeight + e.getWeight() <= maxWeight) {
                result.addItem(e);
                currentWeight += e.getWeight();
            }
        }

        result.calculateTotals();
        return result;
    }

    /**
     * Method to generate all posibile subsets of the experiments if the list is overweight it breaks
     * It only stores valid results
     * 
     * @return top 3 results
     */
    public List<Result> bruteForce() {

        List<Result> validResults = new ArrayList<>();
        int n = items.size();

        for (int i = 0; i < (1 << n); i++) {

            Result result = new Result();
            int totalWeight = 0;

            for (int j = 0; j < n; j++) {

                if ((i & (1 << j)) != 0) {
                    Experiment e = items.get(j);
                    totalWeight += e.getWeight();

                    if (totalWeight > maxWeight)
                        break;

                    result.addItem(e);
                }
            }

            result.calculateTotals();

            if (result.getTotalWeight() <= maxWeight) {
                validResults.add(result);
            }
        }

        validResults.sort((a, b) ->
                b.getTotalRating() - a.getTotalRating());

   
        return validResults.subList(0, Math.min(3, validResults.size()));
    }
    
    /**
     * Getter for copy of experiment list for testing
     * 
     * @return list of experiments
     */
    public List<Experiment> getItems() {
        return new ArrayList<>(items); 
    }
}
