

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pipes {

    public static final class Item {
        public final int value;
        public final int weight;
        public final int initialIndex;

        public Item(int weight, int value, int initialIndex) {
            this.value = value;
            this.weight = weight;
            this.initialIndex = initialIndex;
        }

    }

    //subset sum problem is the special case of knapsack problem where weight of every item is equal to the value of item.

    public static List<Item> knapsack(List<Item> items, int maxCapacity) {
// building dynamic programming table
        int[][] table = new int[items.size() + 1][maxCapacity + 1];

        for (int i = 0; i < items.size(); i++) {

            Item item = items.get(i);
            for (int capacity = 1; capacity <= maxCapacity; capacity++) {
                int prevItemValue = table[i][capacity];
                if (capacity >= item.weight) { // item can be stored in knapsack, check adding
                    int valueFreeingWeightForItem = table[i][capacity -
                    item.weight];
//only if that item is more valuable than the previous one, add it
                    table[i + 1][capacity] = Math.max(valueFreeingWeightForItem +
                            item.value, prevItemValue);
                } else { // there is no capacity for the item
                    table[i + 1][capacity] = prevItemValue;
                }
            }
        }
// find solution in table
        List<Item> solution = new ArrayList<>();
        int capacity = maxCapacity;
        for (int i = items.size(); i > 0; i--) { // going backwards
// have we already chosen that item?
            if (table[i - 1][capacity] != table[i][capacity]) {
                solution.add(items.get(i - 1));
// if so, subtract its weight
                capacity -= items.get(i - 1).weight;
            }
        }
        if (capacity == 0)  return solution;
        else return new ArrayList<>();
    }


    /*public static void findLengthSubset2(Stack<Integer> currentSubset, int targetSum, int start, int n,
                                         ArrayList<Integer> pipesLengths, ArrayList<Integer> res) {
        System.out.println("called func");
        if (start > n) return;
        System.out.println(currentSubset);
        //ak sme uz nasli vysledok
        if (!res.isEmpty()) return;

        //ak posledny prvok doplnil sucet hodnot do ziadanej hodnoty
        if (targetSum == 0) {
            res.addAll(currentSubset);
            return;
        } else if (targetSum < 0) {
            return;
        }

        for (int i = start; i < n; i++) {
            //System.out.println("adding");
            currentSubset.push(i);
            findLengthSubset2(currentSubset, targetSum - pipesLengths.get(currentSubset.peek()),
                    i + 1, n, pipesLengths, res);
            //System.out.println("removing");
            currentSubset.pop();
        }
    }*/


    public static void main(String[] args) {
        ArrayList<Item> pipesLengths = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int len = in.nextInt();
            pipesLengths.add(new Item(len, len, i));
        }
        int subSum = in.nextInt();
        //System.out.println("input end");
        List<Item> res =  knapsack(pipesLengths, subSum);
        if (res.isEmpty()) System.out.println("nemozne");
        else {
            for (int i = 0; i < res.size(); i++) {
                if (i == res.size() - 1) {
                    System.out.println(res.get(i).initialIndex);
                } else System.out.print(res.get(i).initialIndex + " ");
            }
        }


    }
}

