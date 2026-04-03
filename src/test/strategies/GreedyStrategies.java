package strategies;

import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Suite for GreedyStrategies.java
 * @author Keanu Cruz
 */
public class GreedyStrategies {

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
                new Experiment(100, 50),
                new Experiment(200, 100),
                new Experiment(300, 150)
        );

        Result result = greedyByRating(list);

        assertEquals(totalWeight(result), result.getTotalWeight());
        assertEquals(totalRating(result), result.getTotalRating());
    }

    @Test
    void lightestWeightFirst_StandardOperation(){
        List<Experiment> list = List.of(
                new Experiment(100, 50),
                new Experiment(200, 100),
                new Experiment(300, 150)
        );

        Result result = greedyByWeight(list);

        assertEquals(totalWeight(result), result.getTotalWeight());
        assertEquals(totalRating(result), result.getTotalRating());
    }

    @Test
    void bestWeightRatingRatio_StandardOperation(){
        List<Experiment> list = List.of(
                new Experiment(100, 50),
                new Experiment(200, 100),
                new Experiment(300, 150)
        );

        Result result = greedyByRatio(list);

        assertEquals(totalWeight(result), result.getTotalWeight());
        assertEquals(totalRating(result), result.getTotalRating());
    }

    // Bounds Tests

    @Test
    void highestRatingFirst_DoesNotExceed700kg(){
        fail("Not yet Implemented");
    }

    @Test
     void lightestWeightFirst_DoesNotExceed700kg(){
        fail("Not yet Implemented");
    }

    @Test
    void bestWeightRatingRatio_DoesNotExceed700kg(){
        fail("Not yet Implemented");
    }

    //Does Not Divide by Zero

    @Test
    void bestWeightRatingRatio_DoesNotDivideZero(){
        fail("Not yet Implemented");
    }

    // Empty Lists Tests

    @Test
    void highestRatingFirst_EmptyList(){
        fail("Not yet Implemented");
    }

    @Test
    void lightestWeightFirst_EmptyList(){
        fail("Not yet Implemented");
    }

    @Test
    void bestWeightRatingRatio_EmptyList(){
        fail("Not yet Implemented");
    }

    // All Experiments Too Heavy

    @Test
    void highestRatingFirst_AllExperimentsTooHeavy(){
        fail("Not yet Implemented");
    }

    @Test
    void lightestWeightFirst_AllExperimentsTooHeavy(){
        fail("Not yet Implemented");
    }

    @Test
    void bestWeightRatingRatio_AllExperimentsTooHeavy(){
        fail("Not yet Implemented");
    }

    // High Rating High Weight

    @Test
    void highestRatingFirst_HighRatingHighWeight(){
        fail("Not yet Implemented");
    }

    // Low Weight Low Rating

    @Test
    void lightestWeightFirst_HighRatingHighWeight(){
        fail("Not yet Implemented");
    }

    // Same Weight Same Rating

    @Test
    void highestRatingFirst_SameWeightSameRating(){
        fail("Not yet Implemented");
    }

    @Test
    void lightestWeightFirst_SameWeightSameRating(){
        fail("Not yet Implemented");
    }

    @Test
    void bestWeightRatingRatio_SameWeightSameRating(){
        fail("Not yet Implemented");
    }

    // Same Ratio Multiple Items

    @Test
    void bestWeightRatingRatio_SameRatioMultipleItems(){
        fail("Not yet Implemented");
    }

    // Sort Order Consistency

    @Test
    void highestRatingFirst_SortOrder(){
        fail("Not yet Implemented");
    }

    @Test
    void lightestWeightFirst_SortOrder(){
        fail("Not yet Implemented");
    }

    @Test
    void bestWeightRatingRatio_SortOrder(){
        fail("Not yet Implemented");
    }

}
