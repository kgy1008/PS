import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int result = Integer.compare(Math.abs(o1), Math.abs(o2));
            if (result == 0) {
                return Integer.compare(o1, o2);
            }
            return result;
        });

        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            int num = Integer.parseInt(br.readLine());

            if (num != 0) {  // 추가
                pq.add(num);
            } else {  // 절대값이 가장 작은 값 제거 및 출력
                if (pq.isEmpty()) {
                    sb.append("0\n");
                    continue;
                }
                int temp = pq.poll();
                sb.append(temp).append("\n");
            }
        }
        System.out.println(sb);
    }
}
