import java.util.*;

class Dodgeball {
    public static final int TEAM_SIZE = 5;
    //number of players in both teams
    private int a, b;
    private ArrayList<ArrayList<Integer>> vsTable;

    public Dodgeball(int a, int b) {
        this.a = a;
        this.b = b;
        this.vsTable = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            vsTable.add(new ArrayList<>());
        }
    }

    private static void reverse(int[] arr, int start) {
        int i = start, j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean nextPermutation(int[] perm) {
        boolean cangen = false;
        int i = perm.length - 2;
        while (i >= 0 && perm[i + 1] <= perm[i]) {
            i--;
        }
        if (i >= 0) {
            cangen = true;
            int j = perm.length - 1;
            while (perm[j] <= perm[i]) {
                j--;
            }
            swap(perm, i, j);
        }
        reverse(perm, i + 1);
        return cangen;
    }


    private LinkedList<Integer> createTeamFromLineup(int[] lineup) {
        LinkedList<Integer> team = new LinkedList<>();
        for (int player = 0; player < lineup.length; player++) {
            //if player is presented in lineup
            if (lineup[player] == 1) {
                //add player to team
                team.add(player);
            }
        }
        return team;
    }




//brute force
    private int findMinSumB(List<Integer> teamA) {
        int[] lineupB = createInitialLineup(b);
        int optSumB = Integer.MAX_VALUE;
        do {
            LinkedList<Integer> teamB = createTeamFromLineup(lineupB);
            LinkedList<Integer> captainsB = new LinkedList<>(teamB);
            for (int i = 0; i < TEAM_SIZE; i++) {
                int currentSum = 0;
                int currentCaptain = captainsB.get(i);
                teamB.add(currentCaptain);

                for (int plA:
                        teamA) {
                    for (int plB:
                            teamB) {
                        currentSum += vsTable.get(plA).get(plB);
                    }
                }
                if (currentSum < optSumB) {
                    optSumB = currentSum;
                }
                teamB.removeLast();
            }
        } while (nextPermutation(lineupB));
        return optSumB;
    }

    private static int[] createInitialLineup(int size) {
        int[] lineup = new int[size];
        /*
          First lineup of team A is represented by permutation where first a - 5 elements are 0 and other 5 are 1.
          We will generate all the permutations in lexicographical order later
         */
        for (int i = 0; i < size; i++) {
            if (i < size - TEAM_SIZE) lineup[i] = 0;
            else lineup[i] = 1;
        }
        return lineup;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        Dodgeball dodgeball = new Dodgeball(a, b);

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                dodgeball.vsTable.get(i).add(in.nextInt());
            }
        }
        in.close();

        int[] lineupA = createInitialLineup(a);
        //store optimal sum (maximum of minSumB)
        int optimalSum = Integer.MIN_VALUE;

        do {
            //generating team from lineup (current permutation)
            LinkedList<Integer> teamA = dodgeball.createTeamFromLineup(lineupA);
            //each player from 0 to a - 1 can be captain
            LinkedList<Integer> captainsA = new LinkedList<>(teamA);
            for (int capA = 0; capA < TEAM_SIZE; capA++) {
                //adding captain clone to team
                teamA.add(captainsA.get(capA));
                //teamA is ready, lets choose optimal sum assuming that lineup for team B also must be optimal
                //check optimality with that captain
                int currentSum = dodgeball.findMinSumB(teamA);
                if (currentSum > optimalSum) optimalSum = currentSum;
                teamA.removeLast();
            }
        } while (nextPermutation(lineupA));
        System.out.println(optimalSum);
    }
}
