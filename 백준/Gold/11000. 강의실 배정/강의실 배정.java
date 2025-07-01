import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Teach[] lectures = new Teach[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures[i] = new Teach(start, end);
        }

        Arrays.sort(lectures, (a, b) -> {
            if (a.start == b.start) {
                return Integer.compare(a.end, b.end);
            }
            return Integer.compare(a.start, b.start);
        });

        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();

        for (Teach lecture : lectures) {
            if (!roomEndTimes.isEmpty() && roomEndTimes.peek() <= lecture.start) {
                roomEndTimes.poll();
            }
            roomEndTimes.offer(lecture.end);
        }
        
        System.out.println(roomEndTimes.size());
    }

    static class Teach {
        int start, end;

        Teach(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
