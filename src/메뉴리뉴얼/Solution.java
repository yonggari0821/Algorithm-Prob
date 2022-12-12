package 메뉴리뉴얼;

import java.util.*;

public class Solution {

    List<String> ansList = new ArrayList<>();
    Map<String, Integer> PossibleAlphaComb = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {

        for (int i = 0; i < orders.length; i++)
        {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        for (int courseLen : course)
        {
            for (String order : orders)
            {
                MakeComb("", order, courseLen);
                System.out.println("done with courseLen " + courseLen + " and order " + order);
            }

            if(!PossibleAlphaComb.isEmpty())
            {
                List<Integer> countList = new ArrayList<>(PossibleAlphaComb.values());
                int max = Collections.max(countList);

                if (max > 1)
                {
                    for (String key : PossibleAlphaComb.keySet())
                    {
                        if (PossibleAlphaComb.get(key) == max)
                            ansList.add(key);
                    }
                }
                PossibleAlphaComb.clear();
            }
        }

        Collections.sort(ansList);

        String[] answer = new String[ansList.size()];

        for (int i = 0; i < ansList.size(); i++)
            answer[i] = ansList.get(i);

        ansList.clear();

        return answer;
    }

    public void MakeComb(String order, String left, int cnt)
    {
        if (cnt == 0)
        {
            System.out.println("order " + order + " saved!!");
            PossibleAlphaComb.put(order, PossibleAlphaComb.getOrDefault(order, 0) + 1);
            return ;
        }

        for (int i = 0; i < left.length(); i++)
        {
            System.out.println(i + " : " + order + " : " + left + " : " + cnt);
            MakeComb(order + left.charAt(i), left.substring(i + 1), cnt - 1);
            if (left.substring(i+1).equals(""))
                System.out.println("done with starting as " + order);
        }

    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2,3,4};

        System.out.println("answer = " + Arrays.asList(sol.solution(orders, course)));
    }
}

// https://blog.naver.com/yonggari0821/222953200862