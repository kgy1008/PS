import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 풍선의 개수

        HashMap<Integer, Integer> balloons = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int value = Integer.parseInt(st.nextToken());
            deque.add(i); // 풍선 번호를 덱에 추가
            balloons.put(i, value); // 풍선 번호와 값 저장
        }

        StringBuilder result = new StringBuilder();

        int start = deque.pollFirst();
        int count = N;
        while (count-- > 0) {
            result.append(start).append(" "); // 현재 풍선 번호를 결과에 추가
            int v = balloons.get(start);

            if (v > 0) { // 양수일 경우 -> 오른쪽으로 이동
                while (v > 0 && !deque.isEmpty()) {
                    v -= 1;
                    int out = deque.pollFirst(); // 현재 풍선 제거
                    if (v == 0) {
                        start = out;
                        continue;
                    }
                    deque.offerLast(out); // 오른쪽으로 이동
                }
            } else { // 음수일 경우 -> 왼쪽으로 이동
                v = v * -1;
                while (v > 0 && !deque.isEmpty()) {
                    v -= 1;
                    int out = deque.pollLast(); // 현재 풍선 제거
                    if (v == 0) {
                        start = out;
                        continue;
                    }
                    deque.offerFirst(out); // 왼쪽으로 이동
                }
            }
        }

        System.out.println(result.toString().trim());

    }
}
