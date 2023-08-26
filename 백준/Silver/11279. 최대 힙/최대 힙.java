import java.io.*;

public class Main {

    static int N, size;
    static int[] heap;
    static StringBuilder ans = new StringBuilder();
    static void swap(int a, int b)
    {
        int tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }

    static void add(int a)
    {
        heap[++size] = a;
        for (int i = size; i > 1; i/=2)
        {
            if (heap[i/2] < heap[i]) swap(i/2, i);
            else break;
        }
    }

    static void maxOut()
    {
        ans.append(heap[1]).append('\n');
        heap[1] = heap[size];
        heap[size--] = 0;
        int me = 1;
        while(me * 2 <= size)
        {
            int lchild = me * 2;
            int rchild = me * 2 + 1;
            if (heap[me] > heap[lchild] && heap[me] > heap[rchild]) break;
            else if (heap[lchild] > heap[rchild])
            {
                swap(me, lchild);
                me = lchild;
            }
            else
            {
                swap(me, rchild);
                me = rchild;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        heap = new int[100001];
        size = 0;
        for (int n = 1; n <= N; n++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp == 0) // 최댓값 출력
            {
                if (size == 0) ans.append(0).append('\n');
                else maxOut();
            }
            else // 해당 값 배열에 넣기
                add(tmp);
        }

        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}