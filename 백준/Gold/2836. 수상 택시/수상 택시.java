import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        List<Person> reverse = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (s > e) {
                reverse.add(new Person(s, e));
            }
        }

        if (reverse.isEmpty()) {
            System.out.println(m);
            return;
        }

        reverse.sort(Comparator.comparingInt((Person o) -> o.e)
                .thenComparingInt(o -> o.s));

        long total = 0L;

        int cs = reverse.get(0).e;
        int ce = reverse.get(0).s;

        for (int i = 1; i < reverse.size(); i++) {
            int ns = reverse.get(i).e;
            int ne = reverse.get(i).s;

            if (ns <= ce) {
                ce = Math.max(ce, ne);
            } else {
                total += ce - cs;
                cs = ns;
                ce = ne;
            }
        }

        total += ce - cs;
        long result = m + (2 * total);

        System.out.println(result);
    }

    static class Person {
        int s;
        int e;

        Person(int s, int e) {
            this.s = Math.max(s, e);
            this.e = Math.min(s, e);
        }
    }
}
