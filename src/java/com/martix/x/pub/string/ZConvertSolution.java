package com.martix.x.pub.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 12:40 上午 2021/7/8
 * Z 字形变换
 * lc 6
 * <p>
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 */
public class ZConvertSolution {
    /**
     * 按行排序
     * 过从左向右迭代字符串，我们可以轻松地确定字符位于 Z 字形图案中的哪一行
     * <p>
     * 从左到右迭代s，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。
     * 只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变;
     * <p>
     * 时间/空间复杂度：O(n)
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);

            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }

            curRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    /**
     * 按行访问
     * <p>
     * 首先访问 行 0 中的所有字符，接着访问 行 1，然后 行 2，依此类推.
     * <p>
     * 对于所有整数k:
     * 行0 中的字符位于索引k(2⋅numRows−2) 处;
     * 行numRows−1 中的字符位于索引k(2⋅numRows−2)+numRows−1 处;
     * 内部的行i 中的字符位于索引 k(2⋅numRows−2)+i 以及 (k+1)(2⋅numRows−2)−i 处;
     * <p>
     * <p>
     * 时间空间复杂度O(n)
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert_1(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder result = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                result.append(s.charAt(j + i));

                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    result.append(s.charAt(j + cycleLen - i));
                }
            }
        }

        return result.toString();
    }

}
