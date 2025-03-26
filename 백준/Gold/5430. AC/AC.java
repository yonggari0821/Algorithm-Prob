import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            boolean flipped = false;
            String commands = br.readLine();
            char[] commandsOneByOne = commands.toCharArray();
            int arrLength = Integer.parseInt(br.readLine());
            Deque<Integer> deque = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine(), "[,] ");
            for (int i = 0; i < arrLength; i++) deque.add(Integer.parseInt(st.nextToken()));

            boolean notError = true;
            for (int cm = 0; cm < commandsOneByOne.length; cm++) {
                char curCommand = commandsOneByOne[cm];
                if (curCommand == 'R') flipped = !flipped;
                else {
                    if (deque.isEmpty()) {
                        sb.append("error").append('\n');
                        notError = false;
                        break;
                    }
                    if (flipped) deque.pollLast();
                    else deque.pollFirst();
                }
            }

//            if (notError) {
//                int[] leftOver = new int[deque.size()];
//                for (int i = 0; i < leftOver.length; i++) {
//                    leftOver[i] = flipped ? deque.pollLast() : deque.pollFirst();
//                }
//                sb.append(Arrays.toString(leftOver).trim()).append('\n');
//            }
            if (notError) {
                if (deque.isEmpty()) {
                    sb.append("[]").append('\n');
                } else {
                    sb.append('[');
                    if (flipped) {
                        sb.append(deque.pollLast());
                        while (!deque.isEmpty()) {
                            sb.append(',').append(deque.pollLast());
                        }
                    } else {
                        sb.append(deque.pollFirst());
                        while (!deque.isEmpty()) {
                            sb.append(',').append(deque.pollFirst());
                        }
                    }
                    sb.append(']').append('\n');
                }
            }
        }

        System.out.println(sb.toString());
    }
}
/*
2
D
0
[]
RDRD
3
[96,94,93]

error
[94]
-----------------------
3
RDD
3
[2, 1, 2]
DDR
2
[1, 2]
D
3
[1, 2, 3]

 */