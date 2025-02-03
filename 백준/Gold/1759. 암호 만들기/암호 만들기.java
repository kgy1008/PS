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
    static char[] arr;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        arr = new char[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        combi(0, l, "");
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

    static void combi(int idx, int l, String s) {
        if (l == 0) {
            list.add(s);
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            combi(i + 1, l - 1, s + arr[i]);
        }
    }
}
