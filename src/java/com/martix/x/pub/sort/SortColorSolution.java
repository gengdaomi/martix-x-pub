package com.martix.x.pub.sort;

/**
 * Created by Andrew-Geng on 12:49 上午 2021/5/6
 * 颜色分类 lc 75
 * <p>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 * <p>
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 进阶：
 * <p>
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class SortColorSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};

        new SortColorSolution().sortColors(nums);
        System.out.println(nums);
    }

    /**
     * 两次遍历
     * 通过两次遍历，第一遍遍历首先将 0 归位，第二遍遍历将 1 归位，自然 2 也就被归位了
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int zeroCount = 0; //用于标记0的个数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                this.swap(nums, i, zeroCount);
                zeroCount++;
            }
        }

        for (int i = zeroCount; i < nums.length; i++) {
            if (nums[i] == 1) {
                this.swap(nums, i, zeroCount);
                zeroCount++;
            }
        }
    }

    /**
     * 一次遍历
     * 借用双指针的思路
     *
     * @param nums
     */
    public void sortColors_1(int[] nums) {
        if (nums.length == 1) {
            return;
        }

        int n = nums.length;
        int p0 = 0, p2 = n - 1;

        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }

    /**
     * 交换数组nums 下两个下标的对应值
     * 也可以用^异或的操作方式，来进行两值得交换
     *
     * @param nums
     * @param index1
     * @param index2
     */
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
