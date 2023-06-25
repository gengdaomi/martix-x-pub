package com.martix.x.pub.hw;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Andrew-Geng on 16:20 2023/6/25
 * <p>
 * 明明的随机数
 * <p>
 * 明明生成了
 * N
 * N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 * 数据范围：
 * <p>
 * 1≤n≤1000  ，输入的数字大小满足
 * <p>
 * 1≤val≤500
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 32M，其他语言64M
 * 输入描述：
 * 第一行先输入随机整数的个数 N 。
 * 接下来的 N 行每行输入一个整数，代表明明生成的随机数。
 * 具体格式可以参考下面的"示例"。
 * 输出描述：
 * 输出多行，表示输入数据处理后的结果
 * 示例1
 * 输入例子：
 * 3
 * 2
 * 2
 * 1
 * 输出例子：
 * 1
 * 2
 * 例子说明：
 * 输入解释：
 * 第一个数字是3，也即这个小样例的N=3，说明用计算机生成了3个1到500之间的随机整数，接下来每行一个随机数字，共3行，也即这3个随机数字为：
 * 2
 * 2
 * 1
 * 所以样例的输出为：
 * 1
 * 2
 */
public class MingRandomSolution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int count = in.nextInt();    //随机数总数
            int[] data = new int[count];
            for (int i = 0; i < count; i++) {    //读入生成的随机数
                data[i] = in.nextInt();
            }

            Arrays.sort(data);    //使用库函数排序
            System.out.println(data[0]);    //打印排序后的第一个数（必不重复）

            for (int i = 1; i < count; i++) {    //打印其他数字，需与前面数字比较，不重复才能打印
                if (data[i] != data[i - 1]) {
                    System.out.println(data[i]);
                }
            }
        }
    }
}
