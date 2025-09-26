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

        int n = Integer.parseInt(st.nextToken()); // 수의 개수
        int m = Integer.parseInt(st.nextToken()); // 명령의 개수

        while (leafPointer < n) {
            leafPointer <<= 1;
        }
        tree = new long[leafPointer * 2];

        StringBuilder sb = new StringBuilder();
        for (int M = 0; M < m; M++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 0) { // sum
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());

                int start = Math.min(i, j) - 1 + leafPointer;
                int end = Math.max(i, j) - 1 + leafPointer;
                long result = query(start, end);
                sb.append(result).append('\n');

            } else { // modify
                int i = Integer.parseInt(st.nextToken());
                long k = Long.parseLong(st.nextToken());

                update(i - 1, k);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static long query(int start, int end) {
        long sum = 0L;

        while (start <= end) {
            if (start % 2 == 1) {
                sum += tree[start++];
            }

            if (end % 2 == 0) {
                sum += tree[end--];
            }
            start /= 2;
            end /= 2;
        }
        return sum;
    }

    static void update(int index, long value) {
        int idx = index + leafPointer;
        tree[idx] = value;
        idx /= 2;

        while (idx > 0) {
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
            idx /= 2;
        }
    }
}
