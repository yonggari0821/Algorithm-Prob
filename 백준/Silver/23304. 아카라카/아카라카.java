import java.io.*;
import java.util.*;
public class Main {
    static boolean palinTest(String str)
    {
        int len = str.length();
        if (len == 1) return true;
        int start = 0;
        int end = len - 1;
        int frontStart = 0;
        int frontEnd = len / 2;
        int backStart = len - len / 2;
        int backEnd = len;
        while (start < end)
        {
            if (str.charAt(start++) != str.charAt(end--)) return false;
        }
        if (len > 3) {
            if (!palinTest(str.substring(frontStart, frontEnd))) return false;
            if (!palinTest(str.substring(backStart, backEnd))) return false;
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        if (palinTest(str)) bw.write("AKARAKA");
        else bw.write("IPSELENTI");
        br.close();
        bw.close();
    }
}