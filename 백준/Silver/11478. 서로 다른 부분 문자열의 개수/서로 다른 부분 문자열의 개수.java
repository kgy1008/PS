import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        for (int start = 0; start < str.length(); start++) {
            int end = start + 1;

            while (end <= str.length()) {
                String s = str.substring(start, end);
                if (!set.contains(str.substring(start, end))) {
                    set.add(s);
                }
                end++;
            }
        }
        System.out.println(set.size());
    }
}
