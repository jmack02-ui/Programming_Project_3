//=============================================================================
//Team Name:        Team 1
//Team Members:     Keanu Cruz, Johnathan McElprang, Michael McCleary , Pong Vodmongkol
//Course/Section:   CS 2430
//Project:          Programming Project 3 – Optimal selection(Spring 2026)
//Primary Author:   Keanu Cruz
//=============================================================================
package team1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Suite for BruteForce.java
 * @author Keanu Cruz
 */
public class BruteForceStrategiesTest {

    // Helper Method

    private Result bestResult(List<Result> results){
        Result best = results.get(0);

        for (Result r : results){
            if (r.getTotalRating() > best.getTotalRating()){
                best = r;
            }
        }
        return best;
    }

    // Standard Operation Tests For Brute Force
    @Test
    void standardOperation(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 150, 100),
                new Experiment(3, "C", 300, 250)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        List<Result> results = solver.bruteForce();

        for(Result r : results){
            if(r.getChosenItems().size() == 3){
                return;
            }
        }
        fail();
    }

    @Test
    void optimalCase(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 300, 200),
                new Experiment(2, "B", 200, 150),
                new Experiment(3, "C", 100, 100)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        List<Result> results = solver.bruteForce();

        int bestRating = 0;

        for(Result r : results){
            if(r.getTotalRating() > bestRating){
                bestRating += r.getTotalRating();
            }
        }

        assertEquals(450, bestRating);
    }

    @Test
    void subsetsInBounds(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 400, 100),
                new Experiment(2, "B", 500, 200)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        List<Result> results = solver.bruteForce();

        for (Result r : results){
            assertTrue(r.getTotalWeight() <= 700);
        }
    }

    @Test
    void subsetCountValidation(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 200, 100)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        List<Result> results = solver.bruteForce();

        assertTrue(results.size() <= 4);
    }

    @Test
    void bruteForceOrderIndependence() {

        List<Experiment> listA = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 200, 100),
                new Experiment(3, "C", 300, 200)
        );

        List<Experiment> listB = List.of(
                new Experiment(3, "C", 300, 200),
                new Experiment(2, "B", 200, 100),
                new Experiment(1, "A", 100, 50)
        );

        KnapsackSolver solverA = new KnapsackSolver(listA, 500);
        KnapsackSolver solverB = new KnapsackSolver(listB, 500);

        int bestA = solverA.bruteForce().get(0).getTotalRating();
        int bestB = solverB.bruteForce().get(0).getTotalRating();

        assertEquals(bestA, bestB);
    }

    // Edge Testing
    @Test
    void lessThanThreeSubsets(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 150, 100)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        List<Result> results = solver.bruteForce();

        assertTrue(results.size() <= 3);
    }

    @Test
    void multipleSubsetsSameRating(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 100, 50)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 100);
        Result best = bestResult(solver.bruteForce());

        assertEquals(50, best.getTotalRating());
    }

    @Test
    void noValidSubsets(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 875, 100),
                new Experiment(2, "B", 701, 200)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result best = bestResult(solver.bruteForce());

        assertEquals(0, best.getTotalWeight());
        assertEquals(0, best.getTotalRating());
    }

    @Test
    void emptySubsets(){
        List<Experiment> list = List.of(
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        List<Result> results = solver.bruteForce();

        assertEquals(1, results.size());
        assertEquals(0, results.get(0).getChosenItems().size());
        assertEquals(0, results.get(0).getTotalRating());
        assertEquals(0, results.get(0).getTotalWeight());
    }
}
