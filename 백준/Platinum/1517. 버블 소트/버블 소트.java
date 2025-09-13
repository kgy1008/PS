import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Number> list = new ArrayList<>();
    static int leafPointer = 1;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            list.add(new Number(i, num));
        }
        list.sort(Comparator.comparingInt(o -> o.value)); // 값을 기준으로 오름차순 정렬
        for (int i = 0; i < n; i++) {
            Number number = list.get(i);
            number.value = i + 1;
        }

        list.sort(Comparator.comparingInt(o -> o.idx)); // 인덱스 기준으로 재정렬 (본래 상태로 복귀)

        while (leafPointer < n) {
            leafPointer <<= 1;
        }
        tree = new long[leafPointer * 2];

        long swapCount = 0L;

        for (int i = 0; i < n; i++) {
            int currentRank = list.get(i).value;
            swapCount += query(currentRank + 1, n);
            update(currentRank, 1);
        }

        System.out.println(swapCount);
    }

    public static void update(int i, int delta) {
        int idx = i + leafPointer - 1;
        tree[idx] += delta;
        while (idx > 1) {
            idx /= 2;
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
        }
    }

    public static long query(int from, int to) {
        from += leafPointer - 1;
        to += leafPointer - 1;

        long sum = 0;
        while (from <= to) {
            if (from % 2 == 1) {
                sum += tree[from++];
            }
            if (to % 2 == 0) {
                sum += tree[to--];
            }
            from /= 2;
            to /= 2;
        }
        return sum;
    }

    static class Number {
        int idx;
        int value;

        public Number(final int idx, final int value) {
            this.idx = idx;
            this.value = value;
        }
    }
}
