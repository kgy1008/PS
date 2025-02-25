import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        StringTokenizer st;
        st = new StringTokenizer(today, ".");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        
        HashMap<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            String[] t = term.split(" ");
            int due = Integer.parseInt(t[1]);
            map.put(t[0], due);
        }
        
        List<Integer> answer = new ArrayList<>();
        
        for (int i=0; i<privacies.length; i++) {
            String[] info = privacies[i].split(" ");
            int due = map.get(info[1]);
            st = new StringTokenizer(info[0], ".");
            int y = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken()) + due;
            int d = Integer.parseInt(st.nextToken());
  
            while (m > 12) {
                y++;
                m -= 12;
            }
            
            if (year >= y) {
                if (year > y) {
                    answer.add(i+1);
                } else {
                    if (month >= m) {
                        if (month > m) {
                            answer.add(i+1);
                        } else {
                            if (day >= d) {
                                answer.add(i+1);
                            }
                        }
                    }
                }
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}