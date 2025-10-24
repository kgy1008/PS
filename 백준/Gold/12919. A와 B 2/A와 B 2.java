import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    static String s;
    static HashSet<String> visited = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        String t = br.readLine();
        
        System.out.println(solve(t) ? 1 : 0);
    }

    private static boolean solve(String str) {
        if (str.equals(s)) {
            return true;
        }
        
        if (str.length() <= s.length()) {
            return false;
        }
        
        if (visited.contains(str)) {
            return false;
        }
        visited.add(str);

        boolean result = false;
        
        if (str.charAt(str.length() - 1) == 'A') {
            result = solve(str.substring(0, str.length() - 1));
        }
        
        if (!result && str.charAt(0) == 'B') {
            String reversed = new StringBuilder(str.substring(1)).reverse().toString();
            result = solve(reversed);
        }

        return result;
    }
}
