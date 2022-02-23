import java.util.*;
class Meetings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<String> people = new ArrayList<>();
        HashSet<HashSet<String>> meetings = new HashSet<>();
        for (int i = 0; i < n; i++) {
            people.add(in.next());
        }


        for (int count = 1; count <= n; count++) {
            int diff = n - count;
            for (int i = 0; i <= diff; i++) {
                HashSet<String> meeting = new HashSet<>(people.subList(i, i + count));
                if (meeting.contains("Baska")) {
                    meetings.add(meeting);
                }
            }
        }
        System.out.println(meetings.size());
    }
}
