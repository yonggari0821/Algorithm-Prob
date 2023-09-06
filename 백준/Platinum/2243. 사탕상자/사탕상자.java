import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, A, S;
    static int[] tree;
    static StringBuilder ans;

    // 일반적인 TD 방식의 구간합 query 함수 => 구간합을 구하는 것이 목적
    static int TDquery(int left, int right, int node, int queryLeft, int queryRight)
    {
        if (right < queryLeft || left > queryRight) return 0;
        else if (queryLeft <= left && queryRight >= right) return tree[node];
        else
        {
            int mid = (left + right) / 2;
            int leftVal = TDquery(left, mid, node * 2, queryLeft, queryRight);
            int righttVal = TDquery(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return leftVal + righttVal;
        }
    }
    // 이 문제에 필요한 TD 방식의 구간합 query 함수 => target 번째 사탕의 맛 등급(1~100만)을 구하는 것이 목적
    static int ft_TDquery(int left, int right, int node, int target)
    {
        // 리프 노드 => 사탕 찾음
        if (left == right)
        {
            return right;
        }
        // 내부 노드 =>
        else
        {
            int mid = (left + right) / 2;
            if (tree[node * 2] >= target) // 왼쪽 자식을 봤을 때 여전히 target 보다 구간합이 크거나 같다 == 더 깊은 왼쪽 자식에 target 번째 사탕이 있다.
                return ft_TDquery(left, mid, node * 2, target);
            else
                return ft_TDquery(mid + 1, right, node * 2 + 1, target - tree[node * 2]);
        }
    }

    static void TDupdate(int left, int right, int node, int target, int diff)
    {
        if (left > target || target > right) return;
        else
        {
            tree[node] += diff;
            if (left != right) // 내부 노드
            {
                int mid = (left + right) / 2;
                TDupdate(left, mid, node * 2, target, diff);
                TDupdate(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 사탕 상자에 손 대는 횟수 : 1~10만
        ans = new StringBuilder();
        S = 1; // tree 배열 사이즈
        while (S < 1000000)
            S *= 2;
        tree = new int[2*S]; // 처음에 빈 상자로 시작하니깐 TDinit 함수 자체가 필요 없음!
        for (int n = 0; n < N; n++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            if (A == 1) // B 번째 사탕을 1개 꺼낸다.
            {
                int B = Integer.parseInt(st.nextToken());
                int candyRank = ft_TDquery(1, S, 1, B);
                ans.append(candyRank).append('\n');
                TDupdate(1, S, 1, candyRank, -1);
            }
            else // (A == 2) // B등급 맛 사탕 C개 넣기 or 빼기
            {
                int B2 = Integer.parseInt(st.nextToken()); // B == target
                int C = Integer.parseInt(st.nextToken()); // C == diff
                TDupdate(1, S, 1, B2, C);
            }
            for (int i = 0; i <= 10; i++)
            {
                // System.out.printf("%d ", tree[S + i]);
            }
            // System.out.println("\n");
        }
        System.out.println(ans.toString());
    }
}