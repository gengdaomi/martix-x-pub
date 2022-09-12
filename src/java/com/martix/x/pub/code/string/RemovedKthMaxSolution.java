package com.martix.x.pub.code.string;

/**
 * Created by Andrew-Geng on 2:26 上午 2021/6/28
 * 删除k个数字后的剩余的数字组成是个最大值
 *
 * 类似获取最小值的那个
 */
public class RemovedKthMaxSolution {

    public static void main(String[] args){
        String result = "88881";
        System.out.println(new RemovedKthMaxSolution().removedKthNum(result,1));
    }

    /**
     * 核心：贪心 + 单调栈
     * 在遍历原整数的数字时，让所有数字一个一个入栈，当某个数字需要删除时，让数字出栈。最后，程序把栈中的元素转化成字符串类型的结果
     * 当栈顶元素小于入栈的元素时候，则把栈顶元素出栈，数字入栈
     * 当遍历数字小于栈顶元素时候，则直接入栈
     *
     * 对于每个数字，如果该数字大于栈顶元素，我们就不断地弹出栈顶元素，直到
     * 栈为空
     * 或者新的栈顶元素不小于当前数字
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

            while (index > 0 && c > stack[index - 1] && k > 0) { //当栈顶数字大于遍历到的当前数字时，栈顶数字出栈（相当于删除数字）
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
}
