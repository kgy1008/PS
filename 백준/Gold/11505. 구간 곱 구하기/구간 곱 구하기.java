import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int divider = 1_000_000_007;
    static long[] tree; // 인덱스 트리
    static int leafPointer = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 수의 개수
        int m = Integer.parseInt(st.nextToken());  // 변경이 일어나는 횟수
        int k = Integer.parseInt(st.nextToken());  // 구간 곱을 구하는 횟수

        while (leafPointer < n) {
            leafPointer <<= 1;
        }
        tree = new long[2 * leafPointer];

        for (int i = 0; i < n; i++) { // 입력받은 값으로 리프 노드 채우기
            tree[leafPointer + i] = Long.parseLong(br.readLine()) % divider; // 나머지 값을 저장
        }

        for (int i = leafPointer + n; i < leafPointer * 2; i++) { // 비어있는 리프 노드 채우기
            tree[i] = 1;
        }

        for (int i = leafPointer - 1; i > 0; i--) { // 부모 노드 채우기
            tree[i] = (tree[i * 2] * tree[i * 2 + 1]) % divider; // 자식 노드들 값을 곱한 후 나머지 값을 저장
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k + m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {  // 수 변경 -> b번째 수를 c로 변경
                tree[leafPointer + b - 1] = c % divider;
                update(leafPointer + b - 1);
            } else {  // 구간 곱 구하기
                long answer = query(b, (int) c);
                sb.append(answer).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    static long query(int b, int c) {  // 구간 곱 구하기
        int left = leafPointer + b - 1;
        int right = leafPointer + c - 1;
        long sum = 1;

        while (left <= right) {
            if (left % 2 == 1) {
                sum = (sum * tree[left++]) % divider;
            }

            if (right % 2 == 0) {
                sum = (sum * tree[right--]) % divider;
            }

            left /= 2;
            right /= 2;
        }
        return sum;
    }

    static void update(int idx) {  // 전체 트리 업데이트
        int targetIdx = idx / 2;
        while (targetIdx >= 1) {
            tree[targetIdx] = (tree[targetIdx * 2] * tree[targetIdx * 2 + 1]) % divider;
            targetIdx /= 2;
        }
    }
}
