import java.io.*;
public class Main {
    static int N, size = 0;
    static StringBuilder ans = new StringBuilder();
    static int[] heap;
    static void swapInMinimalHeap(int a, int b)
    {
        int tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }
    static void addToMinimalHeap(int a)
    {
        heap[++size] = a;
        int me = size;
        while (me > 1)
        {
            int parent = me / 2;
            if (heap[parent] > heap[me])
            {
                swapInMinimalHeap(me, parent);
                me = parent;
            }
            else break;
        }
    }
    static void maxOutFromMinimalHeap()
    {
        ans.append(heap[1]).append('\n');
        heap[1] = heap[size];
        heap[size--] = Integer.MAX_VALUE;
        int me = 1;
        while (me * 2 <= size)
        {
            int lchild = me * 2;
            int rchild = me * 2 + 1;
            if (heap[me] < heap[lchild] && heap[me] < heap[rchild]) break;
            else if (heap[lchild] < heap[rchild])
            {
                swapInMinimalHeap(me, lchild);
                me = lchild;
            }
            else
            {
                swapInMinimalHeap(me, rchild);
                me = rchild;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        heap = new int[100001];
        for (int i = 0; i <= 100000; i++)
            heap[i] = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++)
        {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp == 0)
            {
                if (size == 0) ans.append(0).append('\n');
                else maxOutFromMinimalHeap();
            }
            else addToMinimalHeap(tmp);
        }

        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}