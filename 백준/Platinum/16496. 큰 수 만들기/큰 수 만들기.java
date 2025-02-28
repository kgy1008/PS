import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] num = new String[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = st.nextToken();
        }

        Arrays.sort(num, (o1, o2) -> {
            String num1 = o1 + o2;
            String num2 = o2 + o1;
            return (num2.compareTo(num1));
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(num[i]);
        }

        String answer = sb.toString();

        if (answer.charAt(0) == '0') {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}
