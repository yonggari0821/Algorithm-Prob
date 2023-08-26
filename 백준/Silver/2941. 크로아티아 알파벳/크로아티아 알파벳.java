import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        String str = br.readLine();
        int cnt = 0;
        int i = 0;
        while (i < str.length())
        {
            char tmp = str.charAt(i);
            if (tmp == 'c')
            {
                if (i != str.length() - 1 && (str.charAt(i + 1) == '=' || str.charAt(i + 1) == '-')) i++;
            }
            else if (tmp == 'd')
            {
                if (i < str.length() - 2 && str.charAt(i + 1) == 'z' && str.charAt(i + 2) == '=') i+=2;
                else if (i < str.length() - 1 && (str.charAt(i + 1) == '-')) i++;
            }
            else if (tmp == 'l')
            {
                if (i != str.length() - 1 && str.charAt(i + 1) == 'j') i++;
            }
            else if (tmp == 'n')
            {
                if (i != str.length() - 1 && str.charAt(i + 1) == 'j') i++;
            }
            else if (tmp == 's')
            {
                if (i != str.length() - 1 && str.charAt(i + 1) == '=') i++;
            }
            else if (tmp == 'z')
            {
                if (i != str.length() - 1 && str.charAt(i + 1) == '=') i++;
            }
            i++;
            cnt++;
        }
        ans.append(cnt);
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}
// c d l n s z