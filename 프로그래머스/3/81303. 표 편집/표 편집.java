import java.util.*;

class Solution {
    static int pointer;
    
    public String solution(int n, int k, String[] cmd) {
        TreeSet<Integer> set = new TreeSet<>();
        Stack<Integer> deleted = new Stack<>();
        pointer = k;

        for (int i = 0; i < n; i++) {
            set.add(i);
        }

        for (String commands : cmd) {
            if (commands.equals("C")) { // 삭제
                deleted.push(pointer); 
                set.remove(pointer);

                if (set.higher(pointer) != null) { 
                    pointer = set.higher(pointer); 
                } else {
                    pointer = set.lower(pointer); 
                }
            } else if (commands.equals("Z")) { // 복구
                set.add(deleted.pop());
            } else {
                String[] command = commands.split(" ");
                int dist = Integer.parseInt(command[1]);
                
                if (command[0].equals("D")) { // 아래로 이동
                    while (dist-- > 0) {
                        pointer = set.higher(pointer);
                    }
                } else { // 위로 이동
                    while (dist-- > 0) {
                        pointer = set.lower(pointer);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (set.contains(i)) {
                sb.append("O");
            } else {
                sb.append("X");
            }
        }

        return sb.toString();
    }
}
