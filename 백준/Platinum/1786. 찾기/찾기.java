import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder Ps = new StringBuilder();
    static int KMPSearch (String str1, String str2)
    {
        int[] subString = new int[str2.length()];
        int i = 0;
        int j = 1;
        int cnt = 0;
        int ni = 0;
        while (j < str2.length())
        {
            while (i > 0 && str2.charAt(i) != str2.charAt(j))
            {
                i = subString[ i - 1 ];
            }
            if (i == 0 && str2.charAt(i) != str2.charAt(j))
            {
                j++;
            }
            else if (str2.charAt(i) == str2.charAt(j))
            {
                subString[j] = i + 1;
                i++;
                j++;
            }
        }
//        System.out.println(Arrays.toString(subString));
        i = 0;
        j = 0;
        while (i < str1.length() && j < str2.length())
        {
        	if (str1.charAt(i) == str2.charAt(j))
        	{
        		if (j == 0) ni = i;
        		while (i < str1.length() && j < str2.length() && str1.charAt(i) == str2.charAt(j))
        		{
        			i++;
        			j++;
        		}
        		if (j == str2.length())
        		{
        			cnt++;
        			Ps.append(ni + 1).append(" ");
        			j = subString[j - 1];
        			if (j != 0) ni = i - j;
        		}
        		else 
    			{
        			if (j != 0) 
    				{
        				j = subString[j-1];
        				ni = i - j;
    				}
//        			System.out.println("ni = " + ni);
    			}
        	}
        	else 
    		{
        		i++;
        		if (j != 0) j = subString[j - 1];
    		}
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        String str1 = br.readLine();
        String str2 = br.readLine();
        ans.append(KMPSearch(str1, str2)).append('\n').append(Ps);
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}