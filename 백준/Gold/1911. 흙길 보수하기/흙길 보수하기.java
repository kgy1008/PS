import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 물 웅덩이 개수
        int l = Integer.parseInt(st.nextToken()); // 테이프 길이

        Water[] waters = new Water[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            waters[i] = new Water(start, end);
        }
        Arrays.sort(waters, Comparator.comparingInt((Water w) -> w.start).thenComparing(w -> w.end));

        int cnt = 0;
        int idx = 0;
        int end = 0;

        while (idx < n) {
            if (end < waters[idx].end) {
                int h = (int) Math.ceil((double) (waters[idx].end - waters[idx].start) / l);
                cnt += h;
                end = waters[idx].start + h * l;
                idx++;
            }

            if (idx == n) {
                break;
            }

            while (waters[idx].start <= end) {
                int need = (int) Math.ceil((double) (waters[idx].end - end) / l);
                cnt += need;
                end += need * l;

                idx++;
                if (idx == n) {
                    break;
                }
            }
        }

        System.out.println(cnt);
    }

    static class Water {
        int start;
        int end;

        public Water(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
