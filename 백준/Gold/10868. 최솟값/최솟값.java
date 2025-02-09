import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int[] tree; // 인덱스 트리
    static int leafPointer = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        while (leafPointer < n) {
            leafPointer <<= 1;
        }
        tree = new int[2 * leafPointer];

        for (int i = 0; i < n; i++) { // 입력받은 값으로 리프 노드 채우기
            tree[leafPointer + i] = Integer.parseInt(br.readLine());
        }

        for (int i = leafPointer + n; i < leafPointer * 2; i++) { // 비어있는 리프 노드 채우기
            tree[i] = Integer.MAX_VALUE;
        }

        for (int i = leafPointer - 1; i > 0; i--) { // 부모 노드 채우기
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]); // 두 값 중 작은 값을 부모 노드 값으로 -> MIN Tree
        }

        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int answer = query(a, b);
            sb.append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    static int query(int a, int b) {
        int left = a + leafPointer - 1;
        int right = b + leafPointer - 1;
        int min = Integer.MAX_VALUE;

        while (left <= right) {
            if (left % 2 == 1) { // 시작점이 오른쪽 노드일 때
                min = Math.min(min, tree[left++]);
            }

            if (right % 2 == 0) { // 끝점이 왼쪽 노드일 때
                min = Math.min(min, tree[right--]);
            }

            left /= 2;
            right /= 2;
        }
        return min;
    }
}
