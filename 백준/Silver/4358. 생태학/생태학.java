import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TreeMap<String, Integer> map = new TreeMap<>();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            map.put(line, map.getOrDefault(line, 0) + 1);
        }

        double total = map.keySet().stream().mapToDouble(map::get).sum();

        map.forEach((tree, value) -> {
            int cnt = value;
            double ratio = (cnt * 100) / total;
            System.out.printf("%s %.4f%n", tree, ratio);
        });
    }
}
