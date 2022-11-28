package 평행;

class Solution {
    public int solution(int[][] dots) {
        int answer = 0;
        double[] incline = new double[dots.length * (dots.length - 1) / 2];

        int i = 0;
        int c = 0;
        while (i < dots.length)
        {
            int j = i + 1;
            while (j < dots.length)
            {
                incline[c] = ((double)(dots[j][1] - dots[i][1]) / (dots[j][0] - dots[i][0]));
                c++;
                j++;
            }
            i++;
        }

        c = 0;
        while (c < incline.length)
        {
            int d = c + 1;
            while (d < incline.length)
            {
                if (incline[c] == incline[d])
                    answer = 1;
                d++;
            }
            c++;
        }
        return answer;
    }
}