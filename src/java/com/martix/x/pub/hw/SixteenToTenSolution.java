package com.martix.x.pub.hw;

import java.util.Scanner;

/**
 * Created by Andrew-Geng on 15:58 2023/6/25
 *
 * 进制转换
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示
 *
 * 输入描述：
 * 输入一个十六进制的数值字符串。
 *
 * 输出描述：
 * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
 */
public class SixteenToTenSolution {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        str = str.substring(2);
        int i = Integer.parseInt(str,16);
        System.out.println(i);
    }
}
