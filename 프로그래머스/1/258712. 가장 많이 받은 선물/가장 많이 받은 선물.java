import java.util.*;

class Solution {
    
    public int solution(String[] friends, String[] gifts) {
        int[][] count = new int[friends.length][friends.length];
        
        // 주고 받은 선물 기록 저장
        for (String gift : gifts) {
            String[] person = gift.split(" ");
            String from = person[0];
            String to = person[1];
            
            int fIdx = findIdx(friends, from);
            int tIdx = findIdx(friends, to);
            
            count[fIdx][tIdx]++;
        }
        
        // 선물 지수 계산
        int[] point = new int[friends.length];
        for (int i=0; i<friends.length; i++) {
            int c = 0;
            for (int j=0; j<friends.length; j++) {
                c+=count[i][j];
                c-=count[j][i];
            }
            point[i] = c;
        }
        
        // 최종 몇개의 선물을 받는지 계산
        int[] total = new int[friends.length];
        for (int i=0; i<friends.length; i++) {
            for (int j=0; j<friends.length; j++) {
                if (count[i][j] > count[j][i]) {
                    total[i]++;
                } else if (count[i][j] == count[j][i]) {
                    // 선물 지수로 판단
                    if (point[i] > point[j]) { // 선물 지수가 크다면
                        total[i]++;
                    }
                }
            }
        }
        
        return Arrays.stream(total).max().getAsInt();
    }
    
    private static int findIdx(String[] friends, String target) {
        for (int i=0; i<friends.length; i++) {
            if (friends[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}