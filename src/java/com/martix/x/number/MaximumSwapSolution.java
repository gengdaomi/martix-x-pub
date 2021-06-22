package com.martix.x.number;

/**
 * 最大交换 lc 670
 * 给定非空非负整数，最多交换其中的两个数字，得到最大的整数，返回最大整数
 * <p>
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * <p>
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 * <p>
 * Created By Andrew-Geng on 2020/5/13 10:28 上午
 */
public class MaximumSwapSolution {

    public static void main(String[] args) {
        MaximumSwapSolution maximumSwapSolution = new MaximumSwapSolution();
        int result = maximumSwapSolution.maximumSwap1(2376);

        System.out.println(result);
    }

    /**
     * 解决方案1：
     * 首先以例子分析要交换的特性，我们会发现，当整数中的所有数字均按照非递增的顺序排序，那么这个整数就是最大整数，不需要进行交换；
     * 所以根据上面的分析，我们需要寻找整数中数字出现不符合非递增规则的分裂点，记录违规的分裂点；
     * 在分裂点的后半部分寻找最大的数字，并且位置越靠后的数字相对前面与其相等的前面的数字交换的意义更大，也就是我们要求寻找digits[j] >= max，注意这里是>=；
     * 在分裂点的前半部分从后向前寻找小于上一步找到的max的最大值；
     * 将找到的两个位置数字进行交换，即可得到交换最大整数。
     * 注意，为了进行操作，需先将整数转化为char数组，并在交换后转换回整数
     * <p>
     * 时间复杂度：O(n)，空间复杂度：O(n)
     *
     * @param num
     * @return
     */
    public int maximumSwap1(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        if (digits.length == 1) return num;

        // 寻找不符合非递增顺序的分界线
        int split = 0;
        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i] < digits[i + 1]) {
                split = i + 1;
                break;
            }
        }

        // 在分界线后面的部分寻找最大的值max
        char max = digits[split];
        int index1 = split;
        for (int j = split + 1; j < digits.length; j++) {
            if (digits[j] >= max) {
                max = digits[j];
                index1 = j;
            }
        }

        // 在分界线前面的部分向前寻找小于max的最大值
        int index2 = split;
        for (int i = split - 1; i >= 0; i--) {
            if (digits[i] >= max) {
                break;
            }
            index2--;
        }

        //交换两位找到的char
        char temp = digits[index1];
        digits[index1] = digits[index2];
        digits[index2] = temp;

        return Integer.valueOf(new String(digits));
    }

    /**
     * * 解决方案2：
     * * 利用桶的思想。
     * *
     * * 利用indices桶数组记录数字0〜9的最后位置。
     * * 遍历整个整数变为char的数组，从左到右提取每一个数字；
     * * 对提取到的每一个数字检查是否存在比它更大的数字存在，故与桶的位置数字做比较（从9开始到当前数字)；
     * * 如果，存在比提取的数字更大的桶，我们还需要确保这个较大数字的位置落后于桶数字所在的位置；
     * * 如果找到，则交换两个位置，转换数组并返回。
     * * 这种解法更加巧妙。
     * *
     * *时间复杂度：O(n)，空间复杂度：O(n)
     *
     * @param num
     * @return
     */
    public int maximumSwap2(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        int[] indices = new int[10];
        int result = num;

        for (int i = 0; i < digits.length; i++) {
            indices[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length - 1; i++) {
            int digit = digits[i] - '0';

            for (int j = 9; j > digit; j--) {

                if (indices[j] > i) {
                    char temp = digits[i];
                    digits[i] = digits[indices[j]];
                    digits[indices[j]] = temp;

                    result = Integer.parseInt(new String(digits));
                    return result;
                }
            }
        }

        return result;
    }

}
