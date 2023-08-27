import java.io.*;
import java.util.*;

public class Main {
    static void changeSwitch(boolean[] switches, int gender, int place)
    {
        if (gender == 1) // 남학색
        {
            for (int i = 1; place * i < switches.length; i++) switches[place * i] = !switches[place * i];
        }
        else //
        {
            switches[place] = !switches[place];
            int i = 1;
            while (place - i > 0 && place + i < switches.length)
            {
                if (switches[place - i] == switches[place + i])
                {
                    switches[place - i] = !switches[place + i];
                    switches[place + i] = switches[place - i];
                    i++;
                }
                else break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int switchNum = Integer.parseInt(br.readLine());
        boolean[] switches = new boolean[switchNum + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= switchNum; i++) switches[i] = (Integer.parseInt(st.nextToken()) == 1) ? true : false;
        int studentNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentNum; i++)
        {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int place = Integer.parseInt(st.nextToken());
            changeSwitch(switches, gender, place);
        }
        for (int i = 1; i <= Math.min(20, switchNum); i++)
        {
            if (switches[i]) ans.append(1).append(" ");
            else ans.append(0).append(" ");
        }
        if (switchNum > 20)
        {
            ans.append('\n');
            for (int i = 1; i < switchNum/20; i++)
            {
                for (int j = 1; j <= 20; j++)
                {
                    if (switches[i * 20 + j]) ans.append(1).append(" ");
                    else ans.append(0).append(" ");
                }
                ans.append('\n');
            }
            for (int i = 1; i <= switchNum % 20; i++) {
                if (switches[20 * (switchNum / 20) + i]) ans.append(1).append(" ");
                else ans.append(0).append(" ");
            }
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
/*
25
0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1
1 1
 */