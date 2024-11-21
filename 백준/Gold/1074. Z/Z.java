//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int answer;

    public Main() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2.0, (double) n);
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        solve(size, r, c);
        System.out.println(answer);
    }

    private static void solve(int size, int r, int c) {
        if (size == 1) {
            return;
        }

        int half = size / 2;
        if (r < half && c < half) {
            solve(half, r, c);
        } else if (r < half && c >= half) {
            answer += half * half;
            solve(half, r, c - half);
        } else if (r >= half && c < half) {
            answer += 2 * half * half;
            solve(half, r - half, c);
        } else {
            answer += 3 * half * half;
            solve(half, r - half, c - half);
        }

    }
}
