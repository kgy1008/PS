import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        TreeSet<Integer> active = new TreeSet<>();
        ArrayDeque<Integer> removed = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            active.add(i);
        }

        int idx = k;

        for (String command : cmd) {
            if (command.equals("C")) {
                removed.push(idx); 
                active.remove(idx); 

                if (active.higher(idx) != null) {
                    idx = active.higher(idx);
                } else {
                    idx = active.lower(idx);
                }
            } else if (command.equals("Z")) {
                int restored = removed.pop(); 
                active.add(restored);
            } else {
                String[] parts = command.split(" ");
                String direction = parts[0];
                int number = Integer.parseInt(parts[1]);

                if (direction.equals("U")) {
                    for (int i = 0; i < number; i++) {
                        idx = active.lower(idx);
                    }
                } else if (direction.equals("D")) {
                    for (int i = 0; i < number; i++) {
                        idx = active.higher(idx);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (active.contains(i)) {
                sb.append("O");
            } else {
                sb.append("X");
            }
        }

        return sb.toString();
    }
}
