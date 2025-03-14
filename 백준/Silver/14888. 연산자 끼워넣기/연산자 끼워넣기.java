import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static HashMap<Integer, Character> map = new HashMap<>();
    static int[] nums;
    static int[] cal;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 수의 개수
        nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        cal = new int[4]; // +, -, *, /
        map.put(0, '+');
        map.put(1, '-');
        map.put(2, '*');
        map.put(3, '/');
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            cal[i] = Integer.parseInt(st.nextToken());  // 연산자의 개수
        }

        combi(n, 0, "");
        System.out.println(max);
        System.out.println(min);
    }

    private static void combi(int n, int idx, String s) {
        if (n == 1) {
            calculate(s + nums[idx]);
        }

        for (int i = 0; i < 4; i++) {
            if (cal[i] > 0) {
                cal[i]--;
                combi(n - 1, idx + 1, s + nums[idx] + map.get(i));
                cal[i]++;
            }
        }
    }

    private static void calculate(String s) {
        StringTokenizer st = new StringTokenizer(s, "+-*/", true);
        int num1 = Integer.parseInt(st.nextToken());
        char cal = st.nextToken().charAt(0);
        int num2 = Integer.parseInt(st.nextToken());
        int sum = parse(num1, num2, cal);

        while (st.hasMoreTokens()) {
            char c = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());
            sum = parse(sum, num, c);
        }

        min = Math.min(sum, min);
        max = Math.max(sum, max);
    }

    private static int parse(int num1, int num2, char cal) {
        if (cal == '+') {
            return num1 + num2;
        }
        if (cal == '-') {
            return num1 - num2;
        }
        if (cal == '*') {
            return num1 * num2;
        }

        return num1 / num2;
    }
}
