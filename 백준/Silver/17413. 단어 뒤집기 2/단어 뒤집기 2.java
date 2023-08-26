import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
//        System.out.println(str.length());
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < str.length())
        {
            if (str.charAt(i) != '<' && str.charAt(i) != ' ')
            {
                while (i < str.length() && str.charAt(i) != '<' && str.charAt(i) != ' ') stack.add(str.charAt(i++));
                while (!stack.isEmpty()) sb.append(stack.pop());
            }
            else if (str.charAt(i) == ' ') sb.append(str.charAt(i++));
            else // str.charAt(i) == '<'
            {
                sb.append(str.charAt(i++));
                while (i < str.length() && str.charAt(i) != '>') sb.append(str.charAt(i++));
                sb.append(str.charAt(i++));
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}