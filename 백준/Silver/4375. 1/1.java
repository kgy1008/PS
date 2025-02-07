import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n;
        while ((n = br.readLine()) != null) {
            int num = Integer.parseInt(n);

            int remainder = 1;
            int len = 1;

            while (remainder % num != 0) {
                remainder = (10 * remainder + 1) % num;
                len++;
            }

            System.out.println(len);
        }
    }
}
