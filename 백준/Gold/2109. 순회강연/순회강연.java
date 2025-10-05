import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            lectures[i] = new Lecture(d, p);
        }

        Arrays.sort(lectures, Comparator.comparingInt(o -> o.d));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Lecture lec : lectures) {
            pq.offer(lec.p);
            
            if (pq.size() > lec.d) {
                pq.poll();
            }
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        System.out.println(sum);
    }

    static class Lecture {
        int d;
        int p;

        public Lecture(int d, int p) {
            this.d = d;
            this.p = p;
        }
    }
}
