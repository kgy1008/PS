import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<Integer> arr = new ArrayList<>();
        String s = br.readLine();
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) == 'I') {
                arr.add(i);
            }
        }

        int interval = 0, result = 0;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) - arr.get(i - 1) == 2) {
                interval++;
            } else {
                interval = 0;
            }

            if (interval >= n) {
                result++;
            }
        }
        System.out.println(result);
    }
}
