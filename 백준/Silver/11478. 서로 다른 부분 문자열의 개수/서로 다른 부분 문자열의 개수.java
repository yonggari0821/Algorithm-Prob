import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                set.add(str.substring(i,j));
            }
        }
        ans.append(set.size());
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}