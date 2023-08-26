import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        String numStr = br.readLine();
        if (numStr.length() < 2)
        {
            System.out.println(0);
            return ;
        }
        char flag = numStr.charAt(0);
        int index = 1;
        while(index < numStr.length())
        {
            if (numStr.charAt(index) != flag)
            {
                cnt++;
                while (index < numStr.length() && numStr.charAt(index) != flag)
                    index++;
            }
            while (index < numStr.length() && numStr.charAt(index) == flag)
                index++;
        }
        br.close();
        System.out.println(cnt);
    }
}