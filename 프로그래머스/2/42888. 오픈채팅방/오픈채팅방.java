import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> nickname = new HashMap<>();
        List<String> ans = new ArrayList<>();
        
        for (String r : record) {
            String[] command = r.split(" ");
            if (!command[0].equals("Leave")) {
                nickname.put(command[1], command[2]);
            }
        }
        
        for (String r : record) {
            String[] command = r.split(" ");
            String user = nickname.get(command[1]);
            
            if (command[0].equals("Leave")) {
                ans.add(String.format("%s님이 나갔습니다.", user));
            }
            else if (command[0].equals("Enter")) {
                ans.add(String.format("%s님이 들어왔습니다.", user));
            }
        }
        
        return ans.toArray(new String[0]);
        
    }
}