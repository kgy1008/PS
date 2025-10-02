import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int count = 0;
        for (int p = 0; p < P; p++) {
            int plane = Integer.parseInt(br.readLine());

            int root = find(plane);

            if (root == 0) {
                break;
            } else {
                count++;
                union(root);
            }
        }

        System.out.println(count);
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x) {
        parent[x] = find(x - 1);
    }
}
