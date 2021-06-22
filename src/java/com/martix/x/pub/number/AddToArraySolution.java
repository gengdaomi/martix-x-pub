package com.martix.x.pub.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrew-Geng on 1:43 上午 2021/5/2
 * 数组形式的整数加法
 * lc 989
 * <p>
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * <p>
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * 示例 2：
 * <p>
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * 示例 3：
 * <p>
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * 示例 4：
 * <p>
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 */
public class AddToArraySolution {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 5};
        System.out.println(new AddToArraySolution().addToArrayForm_1(arr, 3));
    }

    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> result = new ArrayList<>();

        for (int i = num.length - 1; i >= 0; i--) {
            int sum = num[i] + k % 10;
            k /= 10;

            if (sum >= 10) { //当相加的数大于等于10，则要进一位，且当前位只保存个位的值
                k++;
                sum -= 10;
            }

            result.add(sum);
        }

        while (k > 0) {
            result.add(k % 10);
            k /= 10;
        }

        Collections.reverse(result);
        return result;
    }

    /**
     * 另一个思路是将整个加数
     * k 加入数组表示的数的最低位。
     *
     * 上面的例子
     * 123+912，我们把它表示成
     * [1,2,3+912]。然后，我们计算
     * 3+912=915。
     * 5 留在当前这一位，将
     * 910/10=91 以进位的形式加入下一位。
     *
     * 然后，我们再重复这个过程，计算
     * [1,2+91,5]。我们得到
     * 3 留在当前位，将
     * 90/10=9 以进位的形式加入下一位。继而又得到
     * [1+9,3,5]，重复这个过程之后，最终得到结果
     * [1,0,3,5]。
     *
     * @param num
     * @param k
     * @return
     */
    public List<Integer> addToArrayForm_1(int[] num, int k) {
        List<Integer> result = new ArrayList<>();

        for (int i = num.length - 1; i >= 0; i--) {
            if (k > 0 || i >= 0) {
                k += num[i];
            }

            result.add(k % 10);
            k /= 10;
        }

        Collections.reverse(result);
        return result;
    }

}
