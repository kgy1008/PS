import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        int k = Integer.parseInt(st.nextToken()); // 동생의 위치

        if (n >= k) {
            System.out.println(n - k);
            System.out.println(1);
            return;
        }

        int[] time = new int[100001]; // 각 위치까지의 최단 시간을 저장
        int[] count = new int[100001]; // 최단 시간으로 도달하는 방법의 수를 저장

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(n);
        time[n] = 0;
        count[n] = 1;

        int minTime = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int currentPos = queue.poll();

            if (time[currentPos] > minTime) {
                continue;
            }

            int[] nextPositions = {currentPos - 1, currentPos + 1, currentPos * 2};

            for (int nextPos : nextPositions) {
                if (nextPos >= 0 && nextPos <= 100000) {
                    if (time[nextPos] == 0) {
                        time[nextPos] = time[currentPos] + 1;
                        count[nextPos] = count[currentPos];
                        queue.offer(nextPos);

                        if (nextPos == k) {
                            minTime = time[nextPos];
                        }
                    }
                    // 해당 위치에 동일한 최단 시간으로 재방문하는 경우
                    else if (time[nextPos] == time[currentPos] + 1) {
                        count[nextPos] += count[currentPos];
                    }
                }
            }
        }

        System.out.println(minTime);
        System.out.println(count[k]);
    }
}
