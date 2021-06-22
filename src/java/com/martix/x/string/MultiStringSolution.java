package com.martix.x.string;

/**
 * source: bytedancer
 * <p>
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * Created By Andrew-Geng on 2020/5/13 3:04 下午
 */
public class MultiStringSolution {

    public static void main(String[] args) {
        MultiStringSolution multiStringSolution = new MultiStringSolution();

        String result = multiStringSolution.multiply("288", "3");
        System.out.println(result);
    }

    /**
     * 乘法运算的时候每一个都要相乘也就是n*n 当被乘数到达第二位的时候乘以乘数的第一位就要写到第二位开始由此得到
     * 结果摆放的位置按i+j
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        StringBuilder stringBuilder = new StringBuilder();

        char[] a = num1.toCharArray();
        char[] b = num2.toCharArray();

        /*
         * 如果个体是0
         */
        if ((num1.length() == 1 && num1.equals("0")) || (num2.length() == 1 && num2.equals("0"))) {
            return "0";
        }

        /*
         * 两数相乘最大不会超过两位相加的位数
         */
        int result[] = new int[a.length + b.length];

        /*
         * 两数倒向相乘
         * 相减48，应该是char 转换为数字的 一个差值
         */
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = b.length - 1; j >= 0; j--) {
                result[a.length - 1 - i + b.length - 1 - j] += (a[i] - 48) * (b[j] - 48);
            }
        }

        /*
         * 每个数相乘 大于10的时候，进位
         */
        for (int i = 0; i < result.length - 1; i++) {
            if (result[i] >= 10) {
                result[i + 1] += result[i] / 10;
                result[i] = result[i] % 10;
            }
        }

        //从前向后判断是否可以读取也就是第一位是不是为零
        boolean judge = false;
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] != 0) {
                judge = true;
            }

            if (judge) {
                stringBuilder.append(result[i]);
            }
        }

        return stringBuilder.toString();
    }
}
