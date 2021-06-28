package com.martix.x.pub.arrays;

/**
 * Created by Andrew-Geng on 12:01 上午 2021/5/13
 *
 * 删除k个数字后的剩余的数字组成是个最小值 lc 402
 * <p>
 * 给定一个以字符串表示的非负整数num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * <p>
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * <p>
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class RemovedKthMinSolution {

    public static void main(String[] args) {
        String result = new RemovedKthMinSolution().removedKthNum("1432219", 3);
        System.out.println(result);
    }

    /**
     * 核心：贪心 + 单调栈
     * 在遍历原整数的数字时，让所有数字一个一个入栈，当某个数字需要删除时，让数字出栈。最后，程序把栈中的元素转化成字符串类型的结果
     * <p>
     * 当栈顶元素大于入栈的元素时候，则把栈顶元素出栈，数字入栈
     * 当遍历数字大于栈顶元素时候，则直接入栈
     *
     * 对于每个数字，如果该数字小于栈顶元素，我们就不断地弹出栈顶元素，直到栈为空
     * 或者新的栈顶元素不大于当前数字
     * 或者我们已经删除了k位数字
     *
     * @param num
     * @param k
     * @return
     */
    public String removedKthNum(String num, int k) {
        if (num.length() < k) {
            return "0";
        }

        int newLength = num.length() - k; //新数组的长度
        char[] stack = new char[num.length()]; //创建一个栈，用于接收所有的数字

        int index = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            while (index > 0 && c < stack[index - 1] && k > 0) { //当栈顶数字大于遍历到的当前数字时，栈顶数字出栈（相当于删除数字）
                index--;
                k--;
            }

            stack[index++] = c; //遍历到的当前数字入栈
        }

        int offset = 0; //找到栈中第1个非零数字的位置，以此构建新的整数字符串
        while (offset < num.length() && stack[offset] == '0') {
            offset++;
        }

        if (offset > newLength) {
            return "0";
        }

        return offset == newLength ? "0" : new String(stack, offset, newLength - offset);
    }

    /**
     * 性能相对较弱，中间涉及两次循环，和多次的subString
     *
     * @param num
     * @param k
     * @return
     */
    public String removedKthNum_1(String num, int k) {
        if (num.length() <= k) {
            return null;
        }

        for (int i = 0; i < k; i++) {
            boolean hasCut = false;

            /*
             从左到右遍历，找到比自己右侧数字大的数组并删除
             */
            for (int j = 0; j < num.length() - 1; j++) {
                if (num.charAt(j) > num.charAt(j + 1)) {
                    num = num.substring(0, j) + num.substring(j + 1, num.length());
                    hasCut = true;
                    break;
                }
            }

            /*
             如果没有找到要删除的数字，则删除最后一个数字
             */
            if (!hasCut) {
                num = num.substring(0, num.length() - 1);
            }
        }

        /*
         清除右边开头的数字0
         */
        int start = 0;
        for (int j = 0; j < num.length() - 1; j++) {
            if (num.charAt(j) != '0') {
                break;
            }
            start++;
        }

        num = num.substring(start, num.length());
        if (num.length() == 0) {
            return "0";
        }

        return num;
    }
}
