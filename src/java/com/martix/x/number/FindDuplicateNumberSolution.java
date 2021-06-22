package com.martix.x.number;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 12:48 上午 2021/4/26
 * 寻找重复数 lc 287
 * <p>
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * <p>
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：nums = [1,1]
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：nums = [1,1,2]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 3 * 104
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 */
public class FindDuplicateNumberSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 2, 2};
        System.out.println(new FindDuplicateNumberSolution().findDuplicate(nums));
    }

    /**
     * Floyd 判圈算法 的思路，快慢指针
     * <p>
     * 我们先设置慢指针
     * slow 和快指针
     * fast ，慢指针每次走一步，快指针每次走两步，根据「Floyd 判圈算法」两个指针在有环的情况下一定会相遇，此时我们再将
     * slow 放置起点
     * 0，两个指针每次同时移动一步，相遇的点就是答案
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;

        slow = nums[slow];
        fast = nums[nums[fast]];

        while (fast != slow) {
            if (slow == fast) {
                break;
            }

            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    /**
     * 借助hash的思路  value 作为计数器
     * <p>
     * 时间 空间 复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int findDuplicate_1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);

            if (map.get(num) > 1) {
                return num;
            }
        }

        return -1;
    }
}
