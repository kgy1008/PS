import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken()); // 플레이어의 수
        int m = Integer.parseInt(st.nextToken()); // 방의 최대 인원

        List<List<Player>> rooms = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());

            int level = Integer.parseInt(st.nextToken());
            String nickname = st.nextToken();
            Player newPlayer = new Player(level, nickname);

            if (rooms.isEmpty()) { // 방이 하나도 존재하지 않으면
                List<Player> newRoom = new ArrayList<>();
                newRoom.add(newPlayer);
                rooms.add(newRoom);
            } else { // 이미 존재하는 방이 있다면
                boolean canJoin = false;
                for (List<Player> room : rooms) {
                    if (room.size() >= m) {
                        continue; // 방이 꽉 찼으면 다음 방으로
                    }
                    Player firstPlayer = room.get(0);
                    if (Math.abs(firstPlayer.level - newPlayer.level) <= 10) {
                        room.add(newPlayer);
                        canJoin = true;
                        break;
                    }
                }

                // 모든 방을 다 확인했는데도 들어가지 못했다면, 새로운 방을 만들어서 넣기
                if (!canJoin) {
                    List<Player> newRoom = new ArrayList<>();
                    newRoom.add(newPlayer);
                    rooms.add(newRoom);
                }
            }
        }

        for (List<Player> room : rooms) {
            if (room.size() == m) {
                System.out.println("Started!");
            } else {
                System.out.println("Waiting!");
            }
            room.sort(Comparator.comparing(o -> o.nickname));
            for (Player player : room) {
                System.out.println(player.level + " " + player.nickname);
            }
        }
    }

    static class Player {
        int level;
        String nickname;

        Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }
    }
}
