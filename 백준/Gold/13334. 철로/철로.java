import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static List<Line> lines = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); /// 사람 수

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int start = Math.min(a, b);
            int end = Math.max(a, b);

            Line line = new Line(start, end);
            lines.add(line);
        }

        lines.sort(Comparator.comparingInt(o -> o.end));

        int d = Integer.parseInt(br.readLine()); // 거리

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int maxCount = 0;

        for (Line line : lines) {
            if (line.end - line.start > d) {
                continue;
            }

            pq.offer(line.start);

            while (!pq.isEmpty() && pq.peek() < line.end - d) {
                pq.poll();
            }

            maxCount = Math.max(maxCount, pq.size());
        }

        System.out.println(maxCount);
    }

    static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
