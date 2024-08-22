import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> users = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();
        
        for (String r : record) {
            String[] information = r.split(" ");
            if (information.length == 3) {
                users.put(information[1], information[2]);
            }
        }
        
        for (String r : record) {
            String[] cmd = r.split(" ");
           if (cmd[0].equals("Enter")) {
                result.add(String.format("%s님이 들어왔습니다.", users.get(cmd[1])));
            }
            else if (cmd[0].equals("Leave")) {
                result.add(String.format("%s님이 나갔습니다.", users.get(cmd[1])));
            }
        }
        
        return result.toArray(new String[0]);
    }
}