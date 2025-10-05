import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int[][] arr = new int[X][Y];
        Deque<Trash> trashes = new ArrayDeque<>();

        for (int x = 0; x < X; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < Y; y++) {
                arr[x][y] = Integer.parseInt(st.nextToken());
                if (arr[x][y] == 1) {
                    trashes.add(new Trash(x, y));
                }
            }
        }

        int answer = 0;

        while (!trashes.isEmpty()) {
            Trash cur = trashes.pop();
            answer++;

            int x = cur.x;
            int y = cur.y;

            Deque<Trash> next = new ArrayDeque<>();
            for (Trash t : trashes) {
                if (t.x >= x && t.y >= y) {
                    x = t.x;
                    y = t.y;
                } else {
                    next.add(t);
                }
            }

            trashes = next;
        }

        System.out.println(answer);
    }

    static class Trash {
        int x, y;

        Trash(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
