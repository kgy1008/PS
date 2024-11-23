import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int size = 2 * n + 1;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                sb.append("I");
            } else {
                sb.append("O");
            }
        }

        String target = sb.toString();

        String s = br.readLine();

        int answer = 0;
        for (int i = 0; i <= m - size; i++) {
            String find = s.substring(i, i + size);
            if (find.equals(target)) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
