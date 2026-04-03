Knapsack Problem – Pseudocode 

Here we go over the general logic and the approach we are using for this project.

---

## Greedy Approach (Highest Rating First)

Start with the full list of experiments

Sort the list so the experiments with the highest rating come first

Set totalWeight to 0
Make an empty list to store the selected experiments

Go through each experiment in the sorted list:
Check if adding it would go over the weight limit
If it does not:
Add it to the list
Add its weight to totalWeight

After going through the list:
Add up the ratings of the selected experiments

Return the selected list, total weight, and total rating

---

## Greedy Approach (Lightest First)

Start with the same list of experiments

Sort the list by weight from smallest to largest

Set totalWeight to 0
Make an empty list for selected experiments

Loop through each experiment:
If it fits within the remaining weight:
Add it to the list
Update totalWeight

After finishing:
Add up the ratings

Return the results

---

## Greedy Approach (Best Ratio)

For each experiment:
Calculate rating divided by weight

Sort the list based on that value (largest first)

Set totalWeight to 0
Make an empty list for selected experiments

Loop through the experiments:
If it fits:
Add it
Update totalWeight

At the end:
Calculate total rating

Return everything

---

## Brute Force Method

Let n be the number of experiments

Set best result to empty
Set best rating to 0

Loop through all possible combinations (0 to 2^n - 1):

```
For each number:
    Treat it like a binary pattern  
    Use it to decide which experiments are included  

Set totalWeight and totalRating to 0  
Make an empty subset  

Go through each bit:
    If the bit is 1:
        Include that experiment  
        Add its weight and rating  

After building the subset:
    If totalWeight is within the limit:
        Check if totalRating is better than what we have so far  
        If it is, update the best result  
```

After checking everything:
Return the best subset found

---

## Dynamic Programming Idea (not implemented)

Make a table that keeps track of the best possible rating for different weights

Go through each experiment one at a time

At each step:
Decide whether including the experiment gives a better result than skipping it

Store the best result in the table so it can be reused later

At the end:
The table will contain the best possible value

Work backwards to figure out which experiments were chosen

---

## General Notes

The greedy methods are simple and fast, but they don’t always give the best answer

Brute force always finds the best answer, but it takes longer since it checks everything

Dynamic programming is kind of in between, since it avoids repeating work and is more efficient for bigger inputs
