import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Set<String> check = new HashSet<>();
        while (n --> 0) {
            check.add(br.readLine());
        }
        List<String> answer = new ArrayList<>();
        while (m --> 0) {
            String k = br.readLine();
            if (check.contains(k)) {
                answer.add(k);
            }
        }
        System.out.println(answer.size());
        answer.sort(String::compareTo);
        answer.stream().forEach(System.out::println);
    }
}
