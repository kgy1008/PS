import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        List<int[]> segments = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            segments.add(new int[]{x, y});
        }

        segments.sort(Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
        
        int answer = 0;
        int currentStart = -1000000000;  // 현재 선분의 시작점
        int currentEnd = -1000000000;    // 현재 선분의 끝점
        
        for (int[] segment : segments) {
            int start = segment[0];
            int end = segment[1];

            if (start > currentEnd) {
                // 겹치지 않으면 이전 구간 길이를 더하고, 새로운 구간 시작
                answer += currentEnd - currentStart;
                currentStart = start;
                currentEnd = end;
            } else {
                // 겹치면 끝점을 확장
                currentEnd = Math.max(currentEnd, end);
            }
        }

        // 마지막 구간 처리
        answer += currentEnd - currentStart;
        
        System.out.println(answer);
    }
}
