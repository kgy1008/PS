import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<Integer> A = new ArrayList<>(n);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        List<Integer> B = new ArrayList<>(m);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        Set<Integer> numbers = new HashSet<>(A);
        numbers.retainAll(B);

        if (numbers.isEmpty()) {
            System.out.println(0);
            return;
        }

        List<Integer> res = new ArrayList<>();

        while (!numbers.isEmpty()) {
            int number = Collections.max(numbers);
            res.add(number);

            int A_idx = A.indexOf(number);
            int B_idx = B.indexOf(number);

            A = A.subList(A_idx + 1, A.size());
            B = B.subList(B_idx + 1, B.size());

            numbers = new HashSet<>(A);
            numbers.retainAll(B);
        }

        System.out.println(res.size());
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
