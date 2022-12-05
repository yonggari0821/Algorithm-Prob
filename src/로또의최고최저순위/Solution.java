package 로또의최고최저순위;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Solution {
    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {};
        answer = new int[2]; //
        int cnt = 0; // 일치하는 갯수
        int zcnt = 0; // 0의 갯수 (사실은 0이거나 중복이라 HashSet에 add 되지 못한 갯수)

        HashSet win = new HashSet();  //  당첨 번호 HashSet
        HashSet pick = new HashSet();  //  고른 번호 HashSet

        //초기화 과정
        for (int i = 0; i < win_nums.length; i++)
            win.add(win_nums[i]);
        for (int i = 0; i < lottos.length; i++)
        {
            if (pick.add(lottos[i]))
                pick.add(lottos[i]); // 넣을 수 있으면 넣고
            else
            {
                pick.add((-1) * i); // 못 넣으면 (중복이면) (조건상 무조건 0이겠지만) 인덱스를 음수화해서 넣음(그럼 무조건 들어감)
            }
        }


        Iterator it1 = win.iterator();
        while (it1.hasNext())
        {
            Object tmp = it1.next();
            if (pick.contains(tmp)) // 고른 번호가 당첨 번호에 포함되면
                cnt++;
        }

        Iterator it2 = pick.iterator();
        while (it2.hasNext())
        {
            int tmp = (int)(it2.next());
            if (tmp <= 0) // 0 또는 중복의 갯수 (무조건 전자겠지만) 만큼 +
                zcnt++;
        }

        int min = 6; // 디폴트 값으로는 1등 6등 설정
        int max = 1;

        if (cnt == 0) // 일치하는 게 없다 1-else) 일치하는 게 없으면서 가능성도 없다 2-if) 일치하는 게 없지만 가능성이 있다.
        {
            min = 6;
            if (zcnt > 0)
                max = 7 - zcnt;
            else
                max = 6;
        }

        else
        {
            min = 7 - cnt;
            max = min - zcnt;
        }

        answer[0] = max;
        answer[1] = min;

        return answer;
    }

    public static void main(String[] args) {
         int[] lottos1 = {44, 1, 0, 0, 31, 25};
        // int[] lottos2 = {0, 0, 0, 0, 0, 0};
        // int[] lottos3 = {45, 4, 35, 20, 3, 9};
         int[] win_nums1 = {31, 10, 45, 1, 6, 19};
        // int[] win_nums2 = {38, 19, 20, 40, 15, 25};
        // int[] win_nums3 = {20, 9, 3, 45, 4, 35};
        System.out.printf("%d\n", solution(lottos1, win_nums1)[0]);
        System.out.printf("%d", solution(lottos1, win_nums1)[1]);
    }
}