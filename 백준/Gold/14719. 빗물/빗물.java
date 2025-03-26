import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] blockHeights = new int[W];
        int maxIndex = 0;
        for (int i = 0; i < W; i++) {
            blockHeights[i] = Integer.parseInt(st.nextToken());
            maxIndex = blockHeights[maxIndex] < blockHeights[i] ? i : maxIndex;
        }

        // 문제 풀이
        // 가장 높이가 높은 블록이 존재하는 곳은 어차피 빗물하고 무관히 벽의 역할만 하기 때문에
        // 해당 점을 기준으로 양쪽으로 나눠서 빗물이 쌓일 수 있는 공간들을 더해가기
        int tmp = 0; // 그 전까지 가장 높은 높이
        int rain = 0; // 빗물 양
        for (int i = 0; i < maxIndex; i++) {
            if (tmp < blockHeights[i]) tmp = blockHeights[i];
            rain += (tmp - blockHeights[i]);
        }
        tmp = 0;
        for (int i = W - 1; i > maxIndex; i--) {
            if (tmp < blockHeights[i]) tmp = blockHeights[i];
            rain += (tmp - blockHeights[i]);
        }

        System.out.println(rain);
    }
}
