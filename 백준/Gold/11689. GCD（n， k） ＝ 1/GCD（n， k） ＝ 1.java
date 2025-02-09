import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        if (n == 1) {
            System.out.println(1);
            return;
        }

        if (isPrime(n)) {
            System.out.println(n - 1);
        } else {
            Set<Long> set = getPrimeFactor(n); // n의 소인수들이 담긴 집합

            double answer = (double) n;
            for (long s : set) {
                answer *= (1 - (1.0 / s));
            }
            System.out.println((long) answer);
        }

    }

    private static Set<Long> getPrimeFactor(long n) {
        Set<Long> set = new HashSet<>();

        while (n % 2 == 0) {  // 짝수 일단 제거
            set.add(2L);
            n /= 2;
        }

        for (long i = 3; i <= Math.sqrt(n); i += 2) {  // 홀수인 소인수 구하기
            while (n % i == 0) {
                set.add(i);
                n /= i;
            }
        }

        if (n > 2) {
            set.add(n);
        }

        return set;
    }

    private static boolean isPrime(long n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
