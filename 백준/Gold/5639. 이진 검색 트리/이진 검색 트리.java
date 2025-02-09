import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int index = 0;
    static int[] preorder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> list = new ArrayList<>();
        String s;
        while ((s = br.readLine()) != null && !s.isEmpty()) {
            int n = Integer.parseInt(s);
            list.add(n);
        }
        preorder = list.stream().mapToInt(Integer::intValue).toArray();
        Node root = buildTree(0, (int) 1e6);
        postorder(root);
    }

    public static Node buildTree(int min, int max) {
        // 배열이 끝났다면 null 반환
        if (index >= preorder.length) {
            return null;
        }

        int cur = preorder[index];
        // 현재 값이 범위 내에 있을 때만 트리를 구성
        if (cur < min || cur > max) {
            return null;
        }

        Node root = new Node(cur);
        index++;

        // 왼쪽 서브트리와 오른쪽 서브트리 재귀적으로 생성
        root.left = buildTree(min, cur);  // 왼쪽 서브트리
        root.right = buildTree(cur, max); // 오른쪽 서브트리

        return root;
    }

    // 트리 후위 순회
    public static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.value);
    }

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
