import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int[] lis;
    static int[] trace;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Line[] lines = new Line[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int b = Integer.parseInt(st.nextToken());

            lines[i] = new Line(i + 1, b);
        }
        br.close();

        Arrays.sort(lines, Comparator.comparingInt(o -> o.a));

        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = lines[i].b;
        }

        lis = new int[n];

        lis[0] = num[0];
        int lastIndex = 0;

        for (int i = 1; i < n; i++) {
            if (lis[lastIndex] < num[i]) {
                lis[++lastIndex] = num[i];
            } else {
                int idx = lowerBound(num[i], lastIndex);
                lis[idx] = num[i];
            }
        }

        int minCount = n - lastIndex - 1;
        System.out.println(minCount);
    }

    static int lowerBound(int target, int right) {
        int left = 0;

        while (left < right) {
            int mid = (left + right) / 2;

            if (lis[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    static class Line {
        int a;
        int b;

        Line(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
