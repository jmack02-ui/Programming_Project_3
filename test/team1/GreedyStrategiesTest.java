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
 * Test Suite for GreedyStrategies.java
 * @author Keanu Cruz
 */
public class GreedyStrategiesTest {

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

    //Standard Operation Tests
    @Test
    void highestRatingFirst_StandardOperation(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 200, 100),
                new Experiment(3, "C", 300, 150)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRating();

        assertEquals(totalWeight(result), result.getTotalWeight());
        assertEquals(totalRating(result), result.getTotalRating());
    }

    @Test
    void lightestWeightFirst_StandardOperation(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 200, 100),
                new Experiment(3, "C", 300, 150)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByWeight();

        assertEquals(totalWeight(result), result.getTotalWeight());
        assertEquals(totalRating(result), result.getTotalRating());
    }

    @Test
    void bestWeightRatingRatio_StandardOperation(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 200, 100),
                new Experiment(3, "C", 300, 150)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRatio();

        assertEquals(totalWeight(result), result.getTotalWeight());
        assertEquals(totalRating(result), result.getTotalRating());
    }

    // Bounds Tests

    @Test
    void highestRatingFirst_DoesNotExceed700kg(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 200, 150),
                new Experiment(2, "B", 400, 200),
                new Experiment(3, "C", 600, 300)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRating();

        assertTrue(result.getTotalWeight() <= 700);
    }

    @Test
     void lightestWeightFirst_DoesNotExceed700kg(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 200, 150),
                new Experiment(2, "B", 400, 200),
                new Experiment(3, "C", 600, 300)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByWeight();

        assertTrue(result.getTotalWeight() <= 700);
    }

    @Test
    void bestWeightRatingRatio_DoesNotExceed700kg(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 200, 150),
                new Experiment(2, "B", 400, 200),
                new Experiment(3, "C", 600, 300)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRatio();

        assertTrue(result.getTotalWeight() <= 700);
    }

    //Does Not Divide by Zero

    @Test
    void bestWeightRatingRatio_DoesNotDivideZero(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 0, 150),
                new Experiment(2, "B", 100, 5)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);

        assertDoesNotThrow( () -> solver.greedyByRatio());
    }

    // Empty Lists Tests

    @Test
    void highestRatingFirst_EmptyList(){
        List<Experiment> list = List.of();

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRating();

        assertTrue(result.getChosenItems().isEmpty());
        assertEquals(0, result.getTotalWeight());
        assertEquals(0, result.getTotalRating());
    }

    @Test
    void lightestWeightFirst_EmptyList(){
        List<Experiment> list = List.of();

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByWeight();

        assertTrue(result.getChosenItems().isEmpty());
        assertEquals(0, result.getTotalWeight());
        assertEquals(0, result.getTotalRating());
    }

    @Test
    void bestWeightRatingRatio_EmptyList(){
        List<Experiment> list = List.of();

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRatio();

        assertTrue(result.getChosenItems().isEmpty());
        assertEquals(0, result.getTotalWeight());
        assertEquals(0, result.getTotalRating());
    }

    // All Experiments Too Heavy

    @Test
    void highestRatingFirst_AllExperimentsTooHeavy_ReturnsEmpty(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 800, 100),
                new Experiment(2, "B", 750, 150),
                new Experiment(3, "C", 701, 200)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRating();

        assertTrue(result.getChosenItems().isEmpty());
        assertEquals(0, result.getTotalWeight());
        assertEquals(0, result.getTotalRating());
    }

    @Test
    void lightestWeightFirst_AllExperimentsTooHeavy(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 800, 100),
                new Experiment(2, "B", 750, 150),
                new Experiment(3, "C", 701, 200)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByWeight();

        assertTrue(result.getChosenItems().isEmpty());
        assertEquals(0, result.getTotalWeight());
        assertEquals(0, result.getTotalRating());
    }

    @Test
    void bestWeightRatingRatio_AllExperimentsTooHeavy(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 800, 100),
                new Experiment(2, "B", 750, 150),
                new Experiment(3, "C", 701, 200)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRatio();

        assertTrue(result.getChosenItems().isEmpty());
        assertEquals(0, result.getTotalWeight());
        assertEquals(0, result.getTotalRating());
    }

    // High Rating High Weight

    @Test
    void highestRatingFirst_HighRatingHighWeight(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 699, 300),
                new Experiment(2, "B", 2, 50),
                new Experiment(3, "C", 100, 150)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRating();

        assertTrue(result.getTotalWeight() <= 700);
    }

    // Low Weight Low Rating

    @Test
    void lightestWeightFirst_LowWeightLowRating(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 69, 30),
                new Experiment(2, "B", 2, 50),
                new Experiment(3, "C", 10, 10)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByWeight();

        assertTrue(result.getTotalWeight() <= 700);
    }

    // Same Weight Same Rating

    @Test
    void highestRatingFirst_SameWeightSameRating(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 100, 50),
                new Experiment(3, "C", 100, 50)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRating();

        assertEquals(3, result.getChosenItems().size());
        assertTrue(result.getTotalWeight() <= 700);
    }

    @Test
    void lightestWeightFirst_SameWeightSameRating(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 100, 50),
                new Experiment(3, "C", 100, 50)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByWeight();

        assertEquals(3, result.getChosenItems().size());
        assertTrue(result.getTotalWeight() <= 700);
    }

    @Test
    void bestWeightRatingRatio_SameWeightSameRating(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 100, 50),
                new Experiment(3, "C", 100, 50)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRatio();

        assertEquals(3, result.getChosenItems().size());
        assertTrue(result.getTotalWeight() <= 700);
    }

    // Same Ratio Multiple Items

    @Test
    void bestWeightRatingRatio_SameRatioMultipleItems(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 200, 100),
                new Experiment(3, "C", 300, 150),
                new Experiment(4, "D", 400, 200)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result result = solver.greedyByRatio();

        assertEquals(3, result.getChosenItems().size());
        assertTrue(result.getTotalWeight() <= 700);
    }

    // Sort Order Consistency

    @Test
    void highestRatingFirst_SortOrder(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 100, 50),
                new Experiment(2, "B", 200, 100),
                new Experiment(3, "C", 400, 200)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 700);
        Result r1 = solver.greedyByRating();

        List<Integer> expected = List.of(3, 2, 1);
        List<Integer> actual = new ArrayList<>();

        for (Experiment e : r1.getChosenItems()) {
            actual.add(e.getId());
        }

        assertEquals(expected, actual);
    }

    @Test
    void lightestWeightFirst_SortOrder(){
        List<Experiment> list1 = List.of(
                new Experiment(2, "B", 200, 100),
                new Experiment(1, "A", 100, 50),
                new Experiment(3, "C", 400, 200)
        );

        KnapsackSolver solver = new KnapsackSolver(list1, 700);
        Result r1 = solver.greedyByWeight();

        List<Integer> expected = List.of(1, 2, 3);
        List<Integer> actual = new ArrayList<>();

        for (Experiment e : r1.getChosenItems()) {
            actual.add(e.getId());
        }

        assertEquals(expected, actual);
    }

    @Test
    void bestWeightRatingRatio_SortOrder(){
        List<Experiment> list1 = List.of(
                new Experiment(1, "A", 150, 50),
                new Experiment(2, "B", 200, 100),
                new Experiment(3, "C", 475, 200)
        );

        KnapsackSolver solver = new KnapsackSolver(list1, 700);
        Result r1 = solver.greedyByRatio();

        List<Integer> expected = List.of(2, 3);
        List<Integer> actual = new ArrayList<>();

        for (Experiment e : r1.getChosenItems()) {
            actual.add(e.getId());
        }

        assertEquals(expected, actual);
    }
}
