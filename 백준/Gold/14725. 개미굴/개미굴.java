import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        TrieNode root = new TrieNode();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            TrieNode current = root;

            for (int j = 0; j < k; j++) {
                String s = st.nextToken();
                current.children.putIfAbsent(s, new TrieNode());
                current = current.children.get(s);
            }
        }

        StringBuilder sb = new StringBuilder();
        dfs(root, sb, 0);
        System.out.print(sb.toString());
    }

    private static void dfs(TrieNode node, StringBuilder sb, int depth) {
        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            sb.append("--".repeat(Math.max(0, depth)));
            sb.append(entry.getKey()).append("\n");
            dfs(entry.getValue(), sb, depth + 1);
        }
    }

    static class TrieNode {
        Map<String, TrieNode> children = new TreeMap<>();
    }
}
