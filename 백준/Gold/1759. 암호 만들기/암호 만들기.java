import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Set<Character> vowel = new HashSet<>(List.of('a', 'e', 'i', 'o', 'u'));
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[] arr = new char[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        combi(arr, 0, 0, l, "");
        for (String word : list) {
            if (isValid(word)) {
                System.out.println(word);
            }
        }
    }

    static boolean isValid(String word) {
        int len = word.length();
        int vowelCnt = vowelCount(word);
        if (vowelCnt >= 1 && len - vowelCnt >= 2) {
            return true;
        }
        return false;
    }

    static int vowelCount(String s) {
        int cnt = 0;
        for (char c : vowel) {
            if (s.contains(String.valueOf(c))) {
                cnt++;
            }
        }
        return cnt;
    }

    static void combi(char[] arr, int idx, int level, int l, String s) {
        if (level == l) {
            list.add(s);
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            combi(arr, i + 1, level + 1, l, s + arr[i]);
        }
    }
}
