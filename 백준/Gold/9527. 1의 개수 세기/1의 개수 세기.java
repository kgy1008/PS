import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        System.out.println(countOnes(b) - countOnes(a - 1));
    }

    static long countOnes(long x) {
        long count = 0;
        for (long num = 1; num <= x; num *= 2) {
            count += ((x + 1) / (num * 2)) * num;
            count += Math.max(0, (x + 1) % (num * 2) - num);
        }
        return count;
    }
}
