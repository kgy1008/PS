import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int leafPointer = 1;
    static int[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 전선의 개수
        HashMap<Integer, Integer> rank = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int pole = Integer.parseInt(st.nextToken());
            rank.put(pole, n - i - 1);
        }

        init();

        st = new StringTokenizer(br.readLine());
        long answer = 0L;
        for (int i = 0; i < n; i++) {
            int pole = Integer.parseInt(st.nextToken());
            int idx = rank.get(pole);
            update(leafPointer + idx);
            answer += query(leafPointer, leafPointer + idx - 1);
        }
        System.out.println(answer);
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

    static void update(int index) {
        int idx = index;
        tree[idx]++;
        idx /= 2;

        while (idx > 0) {
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
            idx /= 2;
        }
    }

    static void init() {
        while (leafPointer < 500_000) {
            leafPointer <<= 1;
        }
        tree = new int[leafPointer * 2];
    }
}
