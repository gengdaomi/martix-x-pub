package com.martix.x.number;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrew-Geng on 1:25 上午 2021/4/26
 * <p>
 * 快乐数 lc 202
 * <p>
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：
 * <p>
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：false
 */
public class HappyNumberSolution {

    public static void main(String[] args) {
        System.out.println(new HappyNumberSolution().getNextNum(23));
    }

    /**
     * 通过双（快慢）指针的方式判定
     * <p>
     * 通过反复调用 getNext(n) 得到的链是一个隐式的链表。隐式意味着我们没有实际的链表节点和指针，但数据仍然形成链表结构。起始数字是链表的头 “节点”，链中的所有其他数字都是节点。next 指针是通过调用 getNext(n) 函数获得。
     * <p>
     * 意识到我们实际有个链表，那么这个问题就可以转换为检测一个链表是否有环。因此我们在这里可以使用弗洛伊德循环查找算法。
     * <p>
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNextNum(n);

        while (fast != slow) {
            if (fast == 1) {
                return true;
            }

            slow = this.getNextNum(slow);
            fast = this.getNextNum(this.getNextNum(fast));
        }

        return slow == 1 ? true : false;
    }

    /**
     * 通过hashSet集合去重，因为如果不是快乐数，那么就会在获取getNext的时候一直出现某个循环数
     * 当出现循环数 或 判定是快乐数后，退出while
     * <p>
     * 时间复杂度：O(logn)
     * 空间复杂度：O(logn)
     *
     * @param n
     * @return
     */
    public boolean isHappy_1(int n) {
        Set<Integer> set = new HashSet<>();

        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = this.getNextNum(n);
        }

        return n == 1 ? true : false;
    }

    /**
     * 通过拆分n，如67，拆分为 6，7 分别平方后相加
     *
     * @param n
     * @return
     */
    public int getNextNum(int n) {
        int sum = 0;
        while (n > 0) {
            int num = n % 10;
            n /= 10;

            sum += num * num;
        }
        return sum;
    }

}
