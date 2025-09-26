import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<int[]> words = new ArrayList<>();
        while (true) {
            String s = br.readLine();

            if (s.equals("-")) {
                break;
            }

            int[] counts = new int[26]; // 문자열의 각 문자 개수
            for (char c : s.toCharArray()) {
                counts[c - 'A']++;
            }
            words.add(counts);
        }

        StringBuilder sb = new StringBuilder();

        while (true) {
            String s = br.readLine();

            if (s.equals("#")) {
                break;
            }

            int[] puzzleCounts = new int[26];
            for (char c : s.toCharArray()) {
                puzzleCounts[c - 'A']++; // 퍼즐판의 각 문자 개수
            }

            Set<Character> puzzleKeys = new HashSet<>();
            for (char c : s.toCharArray()) {
                puzzleKeys.add(c);
            }

            int minCount = Integer.MAX_VALUE;
            TreeSet<Character> minChars = new TreeSet<>();
            int maxCount = Integer.MIN_VALUE;
            TreeSet<Character> maxChars = new TreeSet<>();

            for (char middle : puzzleKeys) {
                int cnt = 0;

                for (int[] wordCounts : words) {
                    boolean canMake = wordCounts[middle - 'A'] != 0; // 가운데 문자는 반드시 포함해야 함

                    if (canMake) {
                        for (int i = 0; i < 26; i++) {
                            if (wordCounts[i] > puzzleCounts[i]) {
                                canMake = false;
                                break;
                            }
                        }
                    }

                    if (canMake) {
                        cnt++;
                    }
                }

                // 최소
                if (cnt < minCount) {
                    minCount = cnt;
                    minChars.clear();
                    minChars.add(middle);
                } else if (cnt == minCount) {
                    minChars.add(middle);
                }
                // 최대
                if (cnt > maxCount) {
                    maxCount = cnt;
                    maxChars.clear();
                    maxChars.add(middle);
                } else if (cnt == maxCount) {
                    maxChars.add(middle);
                }
            }

            sb.append(getString(minChars)).append(' ')
                    .append(minCount).append(' ')
                    .append(getString(maxChars)).append(' ')
                    .append(maxCount).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static String getString(TreeSet<Character> set) {
        StringBuilder sb = new StringBuilder();
        for (char c : set) {
            sb.append(c);
        }
        return sb.toString();
    }
}
