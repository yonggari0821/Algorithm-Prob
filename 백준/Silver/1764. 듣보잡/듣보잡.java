import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n + m; i++) {
            String name = br.readLine();
            map.put(name, map.getOrDefault(name, 0) + 1);
        }

        List<String> NeverHaveIEver = new ArrayList();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 2) {
                NeverHaveIEver.add(entry.getKey());
            }
        }

        Collections.sort(NeverHaveIEver);
        System.out.println(NeverHaveIEver.size());
        for (int i = 0; i < NeverHaveIEver.size(); i++) {
            System.out.println(NeverHaveIEver.get(i));
        }
    }
}
