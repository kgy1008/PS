import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static PriorityQueue<Student> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());  // 사진틀 개수
        int m = Integer.parseInt(br.readLine());  // 총 학생의 추천 횟수

        int[] student = new int[101]; // 학생 번호: 인덱스 & 값: 학생별 추천 횟수
        pq = new PriorityQueue<>((o1, o2) -> {
            int k = Integer.compare(student[o1.id], student[o2.id]); // 추천수가 낮을수록 높은 우선순위 -> 먼저 poll
            if (k == 0) {
                return Integer.compare(o1.index, o2.index); // 먼저 들어온 것이 높은 우선순위를 가짐 -> 먼저 -> poll
            }
            return k;
        });

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int recommend = Integer.parseInt(st.nextToken());
            student[recommend]++;
            Student existingStudent = find(recommend);
            if (existingStudent == null) { // 해당 학생이 존재하지 않을 때
                if (pq.size() >= n) { // 모든 사진틀이 다 사용되었을 때 -> poll
                    Student stu = pq.poll(); // 추천수가 가장 적고 가장 오래된 학생 사진 삭제
                    student[stu.id] = 0; // 추천 수 초기화
                }
                pq.offer(new Student(recommend, i));
            } else {
                pq.remove(existingStudent);
                pq.offer(new Student(recommend, existingStudent.index));
            }
        }

        List<Student> list = new ArrayList<>(pq);
        list.sort(Comparator.comparingInt(o -> o.id));
        StringBuilder sb = new StringBuilder();
        for (Student ans : list) {
            sb.append(ans.id).append(" ");
        }
        System.out.println(sb);
    }

    static Student find(int id) {
        for (Student s : pq) {
            if (s.id == id) {
                return s;
            }
        }
        return null;
    }

    static class Student {
        int id; // 학생 번호
        int index; // 들어온 순서

        public Student(final int id, final int index) {
            this.id = id;
            this.index = index;
        }
    }
}
