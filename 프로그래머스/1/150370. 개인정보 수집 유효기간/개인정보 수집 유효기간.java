import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 오늘 날짜 파싱
        StringTokenizer st = new StringTokenizer(today, ".");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        
        // 약관 종류와 유효 기간 매핑
        HashMap<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            String[] t = term.split(" ");
            int due = Integer.parseInt(t[1]);
            map.put(t[0], due);
        }
        
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] info = privacies[i].split(" ");
            int due = map.get(info[1]);  // 유효기간
            st = new StringTokenizer(info[0], ".");
            int y = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken()) + due;  // 유효기간을 더한 후
            int d = Integer.parseInt(st.nextToken());

            while (m > 12) {
                m -= 12;
                y++;
            }

            if (year > y || (year == y && month > m) || (year == y && month == m && day >= d)) {
                answer.add(i + 1);  
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
