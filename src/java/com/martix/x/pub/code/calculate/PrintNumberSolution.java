package com.martix.x.pub.code.calculate;

/**
 * Created by Andrew-Geng on 15:27 2023/1/5
 * 剑指 Offer 17. 打印从1到最大的n位数
 *
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *  
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 *
 */
public class PrintNumberSolution {

    /**
     * 普通解法 不考虑大数情况
     *
     * 枚举从1 到 10^n-1 ,返回int数组
     *
     * 大数越界问题： 当n 较大时，超出取值范围的数字无法正常存储
     *
     *      * 时间复杂度O(10^n)，从1到10^n-1都遍历一遍
     *      * 空间复杂度O(n)
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        int[] result = new int[(int)Math.pow(10, n) - 1];

        for(int i = 0; i < result.length; i++){
            result[i] = i + 1;
        }

        return result;

    }

    /**
     * 全排列解法 解决大数问题
     *
     * 在数字很大的情况下，哪怕long类型也无法承载，那必须要用字符串保存。
     * 对于本题其实就是对数字0~9的全排列，从1位数0~9的全排列到n位数0~9的全排列，其中要注意的是数字开头不应该有0。
     *
     * 以下是具体步骤
     *
     * 为了避免数字开头出现0，先把首位first固定，first取值范围为1~9
     * 用digit表示要生成的数字的位数，本题要从1位数一直生成到n位数，对每种数字的位数都生成一下首位，所以有个双重for循环
     * 生成首位之后进入递归生成剩下的digit - 1位数，从0~9中取值
     * 递归的中止条件为已经生成了digit位的数字，即index == digit，将此时的数num转为int加到结果res中
     *
     * 时间复杂度O(10^n)，从1到10^n-1都遍历一遍
     * 空间复杂度O(n)
     */
    public int[] printNumbers_1(int n) {
        result = new int[(int)Math.pow(10, n) - 1];

        for(int digit = 1; digit < n + 1; digit++){
            for(char first = '1'; first <= '9'; first++){

                char[] num = new char[digit];
                num[0] = first;
                dfs(1, num, digit);
            }
        }

        return result;

    }

    int[] result;
    int count = 0;

    private void dfs(int index, char[] num, int digit){
        if(index == digit){
            result[count++] = Integer.parseInt(String.valueOf(num));
            return;
        }

        for(char i = '0'; i <= '9'; i++){
            num[index] = i;
            dfs(index + 1, num, digit);
        }
    }
}
