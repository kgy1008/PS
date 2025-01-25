import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer> number = new ArrayList<>();
    static List<String> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            number.add(i);
        }
        number.sort(null);

        combination(0, m, "");

        for (String s : answer) {
            System.out.println(s);
        }
    }

    private static void combination(int idx, int m, String s) {
        if (m == 0) {
            answer.add(s);
            return;
        }

        for (int i = idx; i < number.size(); i++) {
            int n = number.get(i);
            combination(i, m - 1, s + n + " ");
        }
    }
}
