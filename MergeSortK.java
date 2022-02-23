import java.lang.*;
import java.util.*;

/*class MergeSortK {

    int stepCounter = 0;
    public MergeSortK() {

    }


   private void mergeK(ArrayList<ArrayList<Integer>> parts, ArrayList<Integer> res, int n, int k) {
        while (res.size() != n) {
           Integer minValue = Integer.MAX_VALUE;
           int minIndex = 0;
            for (int i = 0; i < parts.size(); i++) {
                if (!parts.get(i).isEmpty()) {
                    //System.out.println(parts);
                    if (Collections.min(parts.get(i)) < minValue) {
                        minValue = Collections.min(parts.get(i));
                        minIndex = i;
                    }
                }
            }
            res.add(minValue);
            parts.get(minIndex).remove(minValue);
        }
       stepCounter+=n*(k-1);
    }

    public ArrayList<Integer> mergeSortK(ArrayList<Integer> a, int k) {
        int n = a.size();
        ArrayList<Integer> res = new ArrayList<>();
        if(n <= 1) {
            System.out.println(0);
            return a;
        } else {
            ArrayList<ArrayList<Integer>> parts = new ArrayList<>();
            int dif = n / k;
            int r = n % k;
            int offset = 0;

                for (int i = 0; i < k - r; i++) {
                   parts.add(new ArrayList<>(a.subList(offset, offset+dif)));
                   offset+=dif;
                }
                for (int i = k - r; i < r; i++) {
                    parts.add(new ArrayList<>(a.subList(offset, offset+dif+1)));
                    offset+=dif+1;
                }
                System.out.println(parts);
            System.out.println(n);
            for (ArrayList<Integer> part:
                 parts) {
                mergeSortK(part, k);
            }
                mergeK(parts, res, n, k);
            System.out.println(n*(k-1));
        }
        stepCounter+=n;
        return res;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        ArrayList<Integer> a = new ArrayList<>(Collections.nCopies(n, 0));
        MergeSortK  mergeSortK = new MergeSortK();
        mergeSortK.mergeSortK(a, k);
        System.out.println(mergeSortK.stepCounter);
    }
}*/
class MergeSortK {
    int stepCounter = 0;

    public int getCounter() {
        return stepCounter;
    }

    private void mergeK(int n, int k) {
        stepCounter+=n*(k-1);
    }

   public void mergeSortK(int n, int k) {
        if (n <= 1) return;
        int d = n/k;
        int r = n%k;
       for (int i = 0; i < k-r; i++) {
           stepCounter+=d;
           mergeSortK(d, k);
       }
       for (int i = 0; i < r; i++) {
           stepCounter+=d+1;
           mergeSortK(d+1, k);
       }
       mergeK(n, k);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        MergeSortK m = new MergeSortK();
        m.mergeSortK(n, k);
        System.out.println(m.stepCounter);
    }

}