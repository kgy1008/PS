import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static final int[] scores = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    static int W, B;
    static char[][] board;
    static boolean[][] visited;
    static TrieNode root;
    static Set<String> foundWords;
    static String longestWord;
    static int totalScore;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        W = Integer.parseInt(br.readLine()); // 단어의 개수
        root = new TrieNode();
        for (int i = 0; i < W; i++) {
            insert(br.readLine());
        }
        br.readLine(); // 빈 줄 처리

        StringBuilder sb = new StringBuilder();
        B = Integer.parseInt(br.readLine()); // 보드의 개수
        for (int b = 0; b < B; b++) {
            board = new char[4][4];
            for (int i = 0; i < 4; i++) {
                String line = br.readLine();
                for (int j = 0; j < 4; j++) {
                    board[i][j] = line.charAt(j);
                }
            }

            foundWords = new HashSet<>();
            longestWord = "";
            totalScore = 0;
            visited = new boolean[4][4];

            // 3. DFS 탐색 시작
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    char startChar = board[i][j];
                    int index = startChar - 'A';
                    if (root.children[index] != null) {
                        visited[i][j] = true;
                        dfs(i, j, String.valueOf(startChar), root.children[index]);
                        visited[i][j] = false;
                    }
                }
            }

            // 4. 결과 출력
            sb.append(totalScore).append(" ").append(longestWord).append(" ").append(foundWords.size()).append("\n");
            if (b < B - 1) {
                br.readLine(); // 다음 보드 전 빈 줄 처리
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'A';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }


    private static void dfs(int x, int y, String currentWord, TrieNode currentNode) {
        if (currentNode.isEndOfWord) { // 단어 완성
            if (foundWords.add(currentWord)) {
                totalScore += scores[currentWord.length()];
                if (currentWord.length() > longestWord.length()) {
                    longestWord = currentWord;
                } else if (currentWord.length() == longestWord.length()) {
                    if (currentWord.compareTo(longestWord) < 0) { // 사전 순으로 더 앞서는 단어 선택
                        longestWord = currentWord;
                    }
                }
            }
        }

        // 단어 길이는 최대 8자
        if (currentWord.length() >= 8) {
            return;
        }

        // 8방향 탐색
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && !visited[nx][ny]) {
                char nextChar = board[nx][ny];
                int index = nextChar - 'A';
                if (currentNode.children[index] != null) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, currentWord + nextChar, currentNode.children[index]);
                    visited[nx][ny] = false;
                }
            }
        }
    }

    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
        }
    }
}
