package 햄버거;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    public static int solution (int [] ingredient) {
        int answer = 0;
        ArrayList<Integer> st = new ArrayList<>();

        for (int i = 0; i < ingredient.length; i++)
            st.add(ingredient[i]);

        for (int i = 0; i < st.size(); i++)
        {
            if (i >= 3 && st.get(i) == 1)
            {
                if (st.get(i-1) == 3)
                {
                    if (st.get(i-2) == 2)
                    {
                        if (st.get(i-3) == 1)
                        {
                            answer++;
                            st.remove(i);
                            st.remove(i-1);
                            st.remove(i-2);
                            st.remove(i-3);
                            i -= 4;
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] ingredient = {2, 1, 0, 2, 3, 1, 0, 3, 1};
        System.out.println(solution(ingredient));
    }
}
