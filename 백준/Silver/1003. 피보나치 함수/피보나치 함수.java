import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] one = new int[41];
        int[] zero = new int[41];

        one[1] = 1; one[2] = 1;
        zero[0] = 1; zero[2] = 1;

        for (int i = 3; i<41; i++) {
            one[i] = one[i-1] + one[i-2];
            zero[i] = zero[i-1] + zero[i-2];
        }

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            System.out.printf("%d %d%n", zero[n], one[n]);
        }
    }
}
