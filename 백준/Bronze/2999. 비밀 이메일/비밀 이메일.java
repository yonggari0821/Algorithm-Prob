import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        String message = br.readLine();
        int len = message.length();
        int mr = 1;
        for (int i = (int) Math.sqrt(len); i >= 1; i--)
        {
            if (len % i == 0)
            {
                mr = i;
                break;
            }
        }
        int mc = len/mr;
//        char[][] originalMessage = new char[mr][mc];
        for (int c = 0; c < mr; c++) {
            for (int r = 0; r < mc; r++) ans.append(message.charAt(mr * r + c));
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}