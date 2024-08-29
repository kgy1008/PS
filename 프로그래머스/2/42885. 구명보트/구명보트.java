import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int fly = 0;
        int pig = people.length-1;
        
        Arrays.sort(people);
        
        while (fly <= pig) {
            if (people[fly] + people[pig] <= limit) {
                answer++;
                fly++;
                pig--;
                continue;
            }
            pig--;
            answer++;
        }
        
        return answer;
    }
}