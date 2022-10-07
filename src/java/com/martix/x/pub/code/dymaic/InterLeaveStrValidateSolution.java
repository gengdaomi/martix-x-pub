package com.martix.x.pub.code.dymaic;

/**
 * Created by Andrew-Geng on 5:18 下午 2021/7/26
 * <p>
 * 判断是否是交错字符串
 * lc 97
 * <p>
 * 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和s2 交错 组成的。
 * <p>
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 */
public class CheckInterLeaveStrSolution {

    /**
     * 动态规划
     *
     * 核心思路：
     *
     * 记|s1|=n， |s2|=m；
     * 首先如果|s1|+|s2|!=|s3|， 那么|s3|必然不是有前两者交错组成；
     *
     * 我们定义f(i,j)表示s1的前i个元素和s2的前j个元素是否能交错组成s3的前i+j个元素；
     * 1.如果s1的前i个元素和s3的前i+j个元素相等，那么s1的前i个元素和s2的前j个元素能组成s3的前i+j个元素
     * 取决于s1的前i-1个元素和s2的前j个元素能否组成s3的前i+j-1个元素，即取决于f(i,j)取决于f(i-1,j)，再此基础上f(i-1,j)为真，f(i,j)也为真；
     *
     * 推到出转移方程：
     * f(i,j)=[f(i-1,j) and s1(i-1)=s3(p)] or [f(i,j-1) and s2(j-1)=s3(p)];
     * 其中p=i+j-1，边界条件f(0,0)=true
     *
     * 复杂度和空间复杂度都是O(nm)
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[n][m];
    }

    /**
     * 优化版
     *
     * 使用滚动数组优化空间复杂度。 因为这里数组f 的第i 行只和第i−1 行相关，所以我们可以用滚动数组优化这个动态规划
     *
     * 时间复杂度是O(nm)
     * 空间复杂度O(m)
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave_1(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[] f = new boolean[m + 1];

        f[0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[j] = f[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    f[j] = f[j] || (f[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[m];
    }

}
