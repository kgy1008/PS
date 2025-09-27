import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Meeting[] meetings = new Meeting[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(start, end);
        }

        Arrays.sort(meetings, (m1, m2) -> {
            if (m1.end != m2.end) {
                return m1.end - m2.end;
            }
            return m1.start - m2.start;
        });

        int count = 0;
        int lastEndTime = 0;

        for (int i = 0; i < n; i++) {
            Meeting current = meetings[i];
            if (current.start >= lastEndTime) {
                lastEndTime = current.end;
                count++;
            }
        }
        System.out.println(count);
    }

    static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
