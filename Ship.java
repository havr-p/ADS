import java.util.*;

class Ship {
    static class Sailor {
        private final int quality;
        private final int isOfficer;
        private final int index;

        public Sailor(int quality, int isOfficer, int index) {
            this.quality = quality;
            this.isOfficer = isOfficer;
            this.index = index;
        }

        public int getQuality() {
            return quality;
        }

        public boolean isOfficer() {
            return (isOfficer == 1);
        }

        @Override
        public String toString() {
            return "Sailor{" +
                    "quality=" + quality +
                    ", isOfficer=" + isOfficer +
                    '}';
        }
    }


    private static LinkedList<Sailor> generateRandomSailors(int n, int d, LinkedList<Integer> officersIndexes) {
        Random random = new Random();
        LinkedList<Sailor> sailors = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int isOfficer = (i < d) ? 1 : 0;
            if (isOfficer == 1) officersIndexes.add(i);
            Sailor sailor = new Sailor(random.nextInt(1000), isOfficer, i);
            sailors.add(sailor);
        }
        return sailors;
    }

    public Ship() {
    }

    private static LinkedList<Sailor> getBestOfficers(LinkedList<Sailor> sailors, LinkedList<Integer> officersIndexes,
                                                      int officersCount) {
        LinkedList<Sailor> bestOfficers = new LinkedList<>();
        officersIndexes.sort((o1, o2) -> {
            Integer quality1 = sailors.get(o1).quality;
            Integer quality2 = sailors.get(o2).quality;
            return quality1.compareTo(quality2);
        });

        for (int i = 0; i < officersCount; i++) {
            int bestIndex = officersIndexes.removeLast();
            Sailor bestOfficer = sailors.remove(bestIndex);
            bestOfficers.add(bestOfficer);
        }
        return bestOfficers;
    }


    /*public void generateAllPossibleCrews(int n, int p, int d, int start,
                          List<Sailor> crew, List<Sailor> sailorList) {
        //System.out.println(crew);
        if (p == crew.size() && crew.stream().filter(Sailor::isOfficer).count() >= d) {
            int crewSum = 0;
            for (Sailor sailor : crew) {
                crewSum += sailor.quality;
            }
            //System.out.println("crewSum " + crewSum);
            //System.out.println("maxSum " + maxSum);
            if (maxSum < crewSum) {
                maxSum = crewSum;
            }
            return;
        }
        for (int i = start; i < n; i++) {
            crew.add(sailorList.get(i));
            generateAllPossibleCrews(n, p, d, i + 1, crew, sailorList);
            crew.remove(crew.size() - 1);
        }
    }*/
    public static long getMaxSum(List<Sailor> optimalCrew, int size) {
        long maxSum = 0;
        for (int i = 0; i < size; i++) {
            maxSum += optimalCrew.get(i).quality;
        }
        return maxSum;
    }


    public static void main(String[] args) {

       ArrayList<Sailor> sailors = new ArrayList<>();
        ArrayList<Sailor> officers = new ArrayList<>();
        ArrayList<Sailor> shipCrew = new ArrayList<>();


        //reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int crewCount = in.nextInt();
        int officersCount = in.nextInt();
        for (int i = 0; i < n; i++) {
            int quality = in.nextInt();
            int isOfficer = in.nextInt();
            Sailor sailor = new Sailor(quality, isOfficer, i);
            if (sailor.isOfficer()) {
                officers.add(sailor);
            } else sailors.add(sailor);
        }

        //  sailors = generateRandomSailors(n, officersCount, officersIndexes);



        officers.sort(Comparator.comparingInt(o -> o.quality));


        //getting best officers

        for (int i = 0; i < officersCount; i++) {
            shipCrew.add(officers.remove(officers.size() - 1));
        }
        sailors.addAll(officers);
        sailors.sort(Comparator.comparingInt(o -> o.quality));




        for (int i = 1; i <= crewCount - officersCount; i++) {
            shipCrew.add(sailors.get(sailors.size() - i));
        }


        System.out.println(getMaxSum(shipCrew, crewCount));

    }
}