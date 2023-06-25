package com.martix.x.pub.hw;

import java.util.Scanner;

/**
 * Created by Andrew-Geng on 16:09 2023/6/25
 * 汽水瓶
 *
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
 * 数据范围：输入的正整数满足
 * 1≤n≤100
 *
 * 输入描述：
 * 输入文件最多包含 10 组测试数据，每个数据占一行，仅包含一个正整数 n（ 1<=n<=100 ），表示小张手上的空汽水瓶数。n=0 表示输入结束，你的程序不应当处理这一行。
 * 输出描述：
 * 对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。
 * 示例1
 * 输入例子：
 * 3
 * 10
 * 81
 * 0
 * 输出例子：
 * 1
 * 5
 * 40
 * 例子说明：
 * 样例 1 解释：用三个空瓶换一瓶汽水，剩一个空瓶无法继续交换
 * 样例 2 解释：用九个空瓶换三瓶汽水，剩四个空瓶再用三个空瓶换一瓶汽水，剩两个空瓶，向老板借一个空瓶再用三个空瓶换一瓶汽水喝完得一个空瓶还给老板
 */
public class BottleSolution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            if(a==0){
                break;
            }else {
                System.out.println(sum(a));
            }
        }
    }

    public static int sum(int n){
        int sum=0;

        while (n>2){
            sum+=n/3; //sum 是目前有n个瓶子可以换汽水的瓶数
            n=n/3+n%3; //然后把换了的汽水喝完，加上刚才没有换的汽水瓶继续换汽水
        }

        if(n==2){ //最后找老板借一个，喝完然后把瓶子还给老板
            sum++;
        }

        return sum;
    }
}
