import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        List<Student> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());

            list.add(new Student(name, korean, english, math));
        }

        list.sort((o1, o2) -> {
            int compare1 = Integer.compare(o2.korean, o1.korean);
            if (compare1 == 0) {
                int compare2 = Integer.compare(o1.english, o2.english);
                if (compare2 == 0) {
                    int compare3 = Integer.compare(o2.math, o1.math);
                    if (compare3 == 0) {
                        return o1.name.compareTo(o2.name);
                    }
                    return compare3;
                }
                return compare2;
            }
            return compare1;
        });

        for (Student student : list) {
            System.out.println(student.name);
        }
    }

    static class Student {
        String name;
        int korean;
        int english;
        int math;

        public Student(final String name, final int korean, final int english, final int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }
    }
}
