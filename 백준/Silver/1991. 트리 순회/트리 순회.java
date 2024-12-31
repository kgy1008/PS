import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static Map<Character, Node> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            tree.putIfAbsent(parent, new Node(parent));
            Node parentNode = tree.get(parent);

            if (left != '.') {
                parentNode.left = new Node(left);
                tree.put(left, parentNode.left);
            }

            if (right != '.') {
                parentNode.right = new Node(right);
                tree.put(right, parentNode.right);
            }
        }

        Node root = tree.get('A');
        StringBuilder sb = new StringBuilder();

        preorder(root, sb);
        sb.append("\n");

        inorder(root, sb);
        sb.append("\n");

        postorder(root, sb);
        sb.append("\n");

        System.out.print(sb);
    }

    // 전위 순회
    static void preorder(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.value);
        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    // 중위 순회
    static void inorder(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        inorder(node.left, sb);
        sb.append(node.value);
        inorder(node.right, sb);
    }

    // 후위 순회
    static void postorder(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        postorder(node.left, sb);
        postorder(node.right, sb);
        sb.append(node.value);
    }

    private static class Node {
        char value;
        Node left, right;

        Node(char value) {
            this.value = value;
        }
    }
}
