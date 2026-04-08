package team1;
//=============================================================================
//File:             Main.java
//Team Name:        Team 1
//Team Members:     Keanu Cruz, Johnathan McElprang, Michael McCleary , Pong Vodmongkol
//Course/Section:   CS 2430
//Project:          Programming Project 3 – Optimal selection(Spring 2026)
//Primary Author:   Implementation Lead (Johnathan)
//=============================================================================

import java.util.ArrayList;
import java.util.List;

/**
 * Main class it creates a list of experiments runs all selection strategies and then prints the results
 * 
 */
public class Main {

    public static void main(String[] args) {

        List<Experiment> experiments = loadExperiments();

        KnapsackSolver solver = new KnapsackSolver(experiments, 700);

        Result rating = solver.greedyByRating();
        Result weight = solver.greedyByWeight();
        Result ratio = solver.greedyByRatio();

        System.out.println("=== Greedy: Highest Rating ===");
        System.out.println(rating);

        System.out.println("=== Greedy: Lightest First ===");
        System.out.println(weight);

        System.out.println("=== Greedy: Best Ratio ===");
        System.out.println(ratio);

        System.out.println("=== Brute Force Top 3 ===");

        List<Result> topResults = solver.bruteForce();

        for (int i = 0; i < topResults.size(); i++) {
            System.out.println("Rank #" + (i + 1));
            System.out.println(topResults.get(i));
        }
    }

    /**
     * Hard code the experiments. 
     * 
     * @return
     */
    public static List<Experiment> loadExperiments() {

        List<Experiment> list = new ArrayList<>();

        list.add(new Experiment(1, "Cloud Patterns", 36, 5));
        list.add(new Experiment(2, "Solar Flares", 264, 9));
        list.add(new Experiment(3, "Solar Power", 188, 6));
        list.add(new Experiment(4, "Binary Stars", 203, 8));
        list.add(new Experiment(5, "Relativity", 104, 8));
        list.add(new Experiment(6, "Seed Viability", 7, 4));
        list.add(new Experiment(7, "Sun Spots", 90, 2));
        list.add(new Experiment(8, "Mice Tumors", 65, 8));
        list.add(new Experiment(9, "Microgravity Plant Growth", 75, 5));
        list.add(new Experiment(10, "Micrometeorites", 170, 9));
        list.add(new Experiment(11, "Cosmic Rays", 80, 7));
        list.add(new Experiment(12, "Yeast Fermentation", 27, 4));

        return list;
    }
}
