//=============================================================================
//Team Name:        Team 1
//Team Members:     Keanu Cruz, Johnathan McElprang, Michael McCleary , Pong Vodmongkol
//Course/Section:   CS 2430
//Project:          Programming Project 3 – Optimal selection(Spring 2026)
//Primary Author:   Keanu Cruz
//=============================================================================
package team1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Suite for Comparison Outputs
 * @author Keanu Cruz
 */
public class ComparisonOutputTest {

    // Helper Methods
    private int totalWeight(Result result){
        int sum = 0;
        for (Experiment e : result.getChosenItems()){
            sum += e.getWeight();
        }
        return sum;
    }

    private int totalRating(Result result){
        int sum = 0;
        for (Experiment e : result.getChosenItems()){
            sum += e.getRating();
        }
        return sum;
    }

    private Result bestResult(List<Result> results){
        Result best = results.get(0);

        for (Result r : results){
            if (r.getTotalRating() > best.getTotalRating()){
                best = r;
            }
        }
        return best;
    }

    // Standard Tests for Output
    @Test
    void output_WorksAsIntended(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        String output = solver.greedyByRating().toString();

        assertNotNull(output);
        assertFalse(output.isEmpty());
    }

    @Test
    void output_CorrectTotals(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 200, 100)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRating();

        assertEquals(totalWeight(result), result.getTotalWeight());
        assertEquals(totalRating(result), result.getTotalRating());
    }

    @Test
    void output_CorrectExperiments(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRating();

        for (Experiment e : result.getChosenItems()){
            assertNotNull(e.toString());
        }
    }

    // Verify Brute Force is better than Greedy Strategies
    @Test
    void greedy_FailsVsBruteForce(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 3, 10),
                new Experiment(2, "B", 4, 40),
                new Experiment(3, "C", 5, 30)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 7);

        Result greedy = solver.greedyByRating();
        Result greedy2 = solver.greedyByWeight();
        Result greedy3 = solver.greedyByRatio();
        Result best = bestResult(solver.bruteForce());

        assertTrue(best.getTotalRating() >= greedy.getTotalRating());
        assertTrue(best.getTotalRating() >= greedy2.getTotalRating());
        assertTrue(best.getTotalRating() >= greedy3.getTotalRating());
    }


    /**
     * Bug Found: 4/5/26 Resolved: 4/6/26
     * Knapsack Constructor not making deep copy of items.
     */
    @Test
    void solverImmuneToMutation() {

        List<Experiment> list = new ArrayList<>();
        list.add(new Experiment(1, "A", 100, 50));

        KnapsackSolver solver = new KnapsackSolver(list, 500);

        list.add(new Experiment(2, "B", 100, 100)); // external mutation

        assertEquals(1, solver.getItems().size());
    }
}
