import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());

            solve(w, k);
        }
    }

    public static void solve(String w, int k) {
        if (k == 1) {
            System.out.println("1 1");
            return;
        }

        // 각 문자의 인덱스를 저장할 리스트 배열
        List<Integer>[] indexPosition = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            indexPosition[i] = new ArrayList<>();
        }

        for (int i = 0; i < w.length(); i++) {
            char ch = w.charAt(i);
            indexPosition[ch - 'a'].add(i);
        }

        int minLen = Integer.MAX_VALUE;
        int maxLen = Integer.MIN_VALUE;
        boolean found = false;

        for (int i = 0; i < 26; i++) {
            List<Integer> indices = indexPosition[i];
            if (indices.size() >= k) {
                found = true;
                // 슬라이딩 윈도우 적용
                for (int j = 0; j <= indices.size() - k; j++) {
                    int start = indices.get(j);
                    int end = indices.get(j + k - 1);
                    int len = end - start + 1;

                    if (len < minLen) {
                        minLen = len;
                    }
                    if (len > maxLen) {
                        maxLen = len;
                    }
                }
            }
        }

        if (found) {
            System.out.println(minLen + " " + maxLen);
        } else {
            System.out.println("-1");
        }
    }
}
