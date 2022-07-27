package string;

public class KMP {

    public static void main(String[] args) {
        KMP test = new KMP();
        System.out.println(test.kmp("ababaabcd", "abaab"));
    }

    public int kmp(String s, String p) {
        char[] cs = s.toCharArray(), cp = p.toCharArray();
        int m = s.length(), n = p.length();
        int[] next = new int[n + 1];
        for (int i = 2, j = 0; i <= n; i++) {
            while (j > 0 && cp[i - 1] != cp[j]) {
                j = next[j];
            }
            if (cp[i - 1] == cp[j]) {
                j++;
            }
            next[i] = j;
        }
        for (int i = 1, j = 0; i <= m; i++) {
            while (j > 0 && cs[i - 1] != cp[j]) {
                j = next[j];
            }
            if (cs[i - 1] == cp[j]) {
                j++;
            }
            if (j == n) {
                return i - n;
            }
        }
        return -1;
    }

}
