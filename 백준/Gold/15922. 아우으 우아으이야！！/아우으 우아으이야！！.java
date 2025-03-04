import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        List<Line> lines = new ArrayList<>();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lines.add(new Line(x, y));
        }

        lines.sort((o1, o2) -> {
            int compare = Integer.compare(o1.x, o2.x);
            if (compare == 0) {
                return Integer.compare(o1.y, o2.y);
            }
            return compare;
        });

        int endY = lines.get(0).y;
        int answer = endY - lines.get(0).x;
        for (int i = 1; i < lines.size(); i++) {
            Line line = lines.get(i);

            if (line.x <= endY) {
                int temp = Math.max(endY, line.y);
                answer += temp - endY;
                endY = temp;
            } else {
                answer += line.y - line.x;
                endY = line.y;
            }
        }

        System.out.println(answer);
    }

    static class Line {
        int x;
        int y;

        public Line(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}
