import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int leafPointer = 1;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 코스의 길이
        int m = Integer.parseInt(st.nextToken()); // 홍준이의 시야 범위

        while (leafPointer < n) {
            leafPointer <<= 1;
        }
        tree = new int[leafPointer * 2];

        // 리프 노드 채우기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            tree[i + leafPointer] = num;
        }

        // 부모 노드 채우기
        for (int i = leafPointer - 1; i > 0; i--) {
            tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
        }

        // 달리기 시작
        StringBuilder sb = new StringBuilder();
        for (int i = m - 1; i < n - m + 1; i++) {
            int left = leafPointer + i - (m - 1);
            int right = leafPointer + i + (m - 1);

            int result = query(left, right);
            sb.append(result).append(' ');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int query(int start, int end) {
        int max = 0;

        while (start <= end) {
            if (start % 2 == 1) {
                max = Math.max(max, tree[start++]);
            }
            if (end % 2 == 0) {
                max = Math.max(max, tree[end--]);
            }
            start /= 2;
            end /= 2;
        }
        return max;
    }
}
