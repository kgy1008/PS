import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String n = br.readLine();

        char[] arr = n.toCharArray();
        Arrays.sort(arr);

        int sum = 0;
        for (char c : arr) {
            sum += (c - '0');
        }

        if (arr[0] != '0' || sum % 3 != 0) { // 30의 배수가 될 수 없음
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }
}
