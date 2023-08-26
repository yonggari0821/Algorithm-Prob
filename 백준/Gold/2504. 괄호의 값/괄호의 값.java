import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main {
    static Stack<Character> stack;
    static int N, ans = 0, tval = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        stack = new Stack<>();
        String str = br.readLine();
        for (int i = 0; i < str.length(); i++)
        {
            char tmp = str.charAt(i);
            if (tmp == '(' || tmp == '[') // 여는 괄호
            {
                stack.push(tmp);
                if (tmp == '(') tval *= 2;
                else tval *= 3;
            }
            else // 닫는 괄호
            {
                if (stack.isEmpty())
                {
                    ans = 0;
                    break;
                }
                else
                {
                    char pair = stack.pop();
                    if (tmp == ')' && pair == '(')
                    {
                        if (str.charAt(i - 1) == '(')
                            ans += tval;
                        tval /= 2;
                    }
                    else if (tmp == ']' && pair == '[')
                    {
                        if (str.charAt(i - 1) == '[')
                            ans += tval;
                        tval /= 3;
                    }
                    else
                    {
                        ans = 0;
                        break;
                    }
                }
            }
            // System.out.println(ans);
        }

        if (!stack.isEmpty()) bw.write('0');
        else
            bw.write(String.valueOf(ans));
        br.close();
        bw.flush();
        bw.close();
    }
}