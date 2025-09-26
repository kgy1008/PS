import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int leafPointer = 1;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 살아온 날
        int Q = Integer.parseInt(st.nextToken()); // 쿼리의 개수

        // 인덱스 트리 크기 설정
        while (leafPointer < n) {
            leafPointer <<= 1;
        }
        tree = new long[leafPointer * 2];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            if (a == 1) { // 생후 p일에 X를 추가
                long x = Long.parseLong(st.nextToken());
                update(p - 1, x);
            } else { // 생후 p일부터 x일까지 변화한 양을 출력
                int q = Integer.parseInt(st.nextToken());
                long result = query(p - 1, q - 1);
                sb.append(result).append('\n');
            }
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void update(int index, long value) {
        int idx = index + leafPointer;
        tree[idx] += value;
        idx /= 2;

        while (idx > 0) {
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
            idx /= 2;
        }
    }

    static long query(int left, int right) {
        left += leafPointer;
        right += leafPointer;
        long sum = 0L;

        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left++];
            }

            if (right % 2 == 0) {
                sum += tree[right--];
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }
}
