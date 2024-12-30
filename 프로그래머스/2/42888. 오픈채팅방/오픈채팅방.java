import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> name = new HashMap<>();
        for (String command : record) {
            String[] commands = command.split(" ");
            
            if (commands[0].equals("Enter")) {
                name.put(commands[1], commands[2]);
            } 
            if (commands[0].equals("Change")) {
                name.put(commands[1], commands[2]);
            }
        }
        
        List<String> answer = new ArrayList<>();
        for (String command : record) {
            String[] commands = command.split(" ");
            if (commands[0].equals("Enter")) {
                answer.add(String.format("%s님이 들어왔습니다.", name.get(commands[1])));
            } 
            if (commands[0].equals("Leave")) {
                answer.add(String.format("%s님이 나갔습니다.", name.get(commands[1])));
            }
        }
        int len = answer.size();
        String[] result = new String[len];
        
        for (int i=0; i<answer.size(); i++) {
            result[i] = answer.get(i);
        }
        return result;
    }
}