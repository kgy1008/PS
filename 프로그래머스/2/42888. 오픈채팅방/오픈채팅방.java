import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> name = new HashMap<>();
        for (String command : record) {
            String[] commands = command.split(" ");
            if (commands.length == 3) {
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
       
        return answer.toArray(new String[0]);
    }
}