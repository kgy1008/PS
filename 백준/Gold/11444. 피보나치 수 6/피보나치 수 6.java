import java.io.*;

public class Main {
    static final long MOD = 1000000007;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        
        if (n == 0) {
            System.out.println(0);
        } else {
            System.out.println(fib(n));
        }
    }

    // 행렬 곱셈 함수 (2x2 행렬)
    public static long[][] matrixMultiply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        result[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD;
        result[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
        result[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
        result[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;
        return result;
    }

    // 행렬 거듭제곱 함수
    public static long[][] matrixPower(long[][] base, long exp) {
        long[][] result = {{1, 0}, {0, 1}}; // 단위 행렬
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = matrixMultiply(result, base);
            }
            base = matrixMultiply(base, base);
            exp /= 2;
        }
        return result;
    }

    // 피보나치 수 계산 함수
    public static long fib(long n) {
        long[][] matrix = {{1, 1}, {1, 0}};
        if (n == 1) {
            return 1;
        }
        long[][] result = matrixPower(matrix, n - 1);
        return result[0][0];
    }
}
