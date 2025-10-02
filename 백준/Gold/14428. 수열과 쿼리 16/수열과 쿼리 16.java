import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 1_000_000_001;
    static int leafPointer = 1;
    static Node[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        while (leafPointer < n) {
            leafPointer <<= 1;
        }

        tree = new Node[leafPointer * 2];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node(INF, INF);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tree[leafPointer + i] = new Node(Integer.parseInt(st.nextToken()), i);
        }

        init();

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int M = 0; M < m; M++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                int i = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken());
                update(i, v);
            } else {
                int i = Integer.parseInt(st.nextToken()) - 1;
                int j = Integer.parseInt(st.nextToken()) - 1;

                Node result = query(i, j);
                sb.append(result.index + 1).append("\n");
            }
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static Node query(int left, int right) {
        left += leafPointer;
        right += leafPointer;

        Node result = new Node(INF, INF);

        while (left <= right) {
            if (left % 2 == 1) {
                result = getNode(result, tree[left]);
                left++;
            }
            if (right % 2 == 0) {
                result = getNode(result, tree[right]);
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return result;
    }

    private static void update(int i, int v) {
        int node = leafPointer + i;
        tree[node] = new Node(v, i);

        while (node > 1) {
            node /= 2;
            tree[node] = getNode(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    private static void init() {
        for (int i = leafPointer - 1; i > 0; i--) {
            tree[i] = getNode(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    private static Node getNode(Node a, Node b) {
        if (a.value < b.value) {
            return a;
        } else if (b.value < a.value) {
            return b;
        } else {
            if (a.index < b.index) {
                return a;
            } else {
                return b;
            }
        }
    }

    static class Node {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
