package strategies;

import org.junit.jupiter.api.Test;

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

    // Tests for Comparison Output
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

    // Correct Totals
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

    // Correct Experiment Outputs
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

    // Greedy Fails vs Brute Force
    @Test
    void greedy_FailsVsBruteForce(){
        List<Experiment> list = List.of(
                new Experiment(1, "A", 3, 10),
                new Experiment(2, "B", 4, 40),
                new Experiment(3, "C", 5, 30)
        );

        KnapsackSolver solver = new KnapsackSolver(list, 7);

        Result greedy = solver.greedyByRating();
        Result best = bestResult(solver.bruteForce());

        assertTrue(best.getTotalRating() >= greedy.getTotalRating());
        assertNotEquals(best.getTotalRating(), greedy.getTotalRating());
    }
}
