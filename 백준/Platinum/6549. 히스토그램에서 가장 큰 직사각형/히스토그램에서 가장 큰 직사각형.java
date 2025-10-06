import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());

            if (n == 0) {
                break;
            }

            long[] heights = new long[n];
            for (int i = 0; i < n; i++) {
                heights[i] = Long.parseLong(st.nextToken());
            }

            Stack<Block> stack = new Stack<>();
            long answer = 0L;

            for (int i = 0; i < n; i++) {
                long h = heights[i];
                int index = i;

                while (!stack.isEmpty() && stack.peek().height >= h) {
                    Block block = stack.pop();

                    long width = i - block.index;
                    answer = Math.max(answer, block.height * width);

                    index = block.index;
                }
                stack.push(new Block(h, index));
            }

            while (!stack.isEmpty()) {
                Block block = stack.pop();
                long width = n - block.index;
                answer = Math.max(answer, block.height * width);
            }
            sb.append(answer).append("\n");
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Block {
        long height;
        int index;

        public Block(long height, int index) {
            this.height = height;
            this.index = index;
        }
    }
}
