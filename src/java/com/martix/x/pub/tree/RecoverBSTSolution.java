package com.martix.x.pub.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Andrew-Geng on 9:21 下午 2021/7/17
 * 恢复二叉搜索树 lc 99
 * <p>
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 * <p>
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 */
public class RecoverBSTSolution {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 6, 5, 4};
        List<Integer> list = List.of(1, 2, 3, 6, 5, 4);
        int[] result = new RecoverBSTSolution().findTwoSwapped(list);
        System.out.println(result);
    }

    /**
     * 思路：
     * 于我们只关心中序遍历的值序列中每个相邻的位置的大小关系是否满足条件，且错误交换后最多两个位置不满足条件，因此在中序遍历的过程我们只需要维护当前中序遍历到的最后一个节点
     * pred，然后在遍历到下一个节点的时候，看两个节点的值是否满足前者小于后者即可，如果不满足说明找到了一个交换的节点，且在找到两次以后就可以终止遍历；
     *
     * 这样我们就可以在中序遍历中直接找到被错误交换的两个节点x 和y，不用显式建立nums 数组。
     * @param root
     */
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (pred != null && root.val < pred.val) {
                y = root;

                if (x == null) {
                    x = pred;
                } else {
                    break;
                }
            }

            pred = root;
            root = root.right;
        }

        this.swap(x, y);
    }

    public void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }


    /**
     * 显式中序遍历
     * 1.找到二叉搜索树中序遍历得到值序列的不满足条件的位置。
     * 2.如果有两个，我们记为i 和j,那么对应被错误交换的节点,分别记为x 和y。
     * 3.如果有一个，我们记为i；
     * 4.交换x 和y 两个节点即可
     * <p>
     * 本方法开辟一个新数组nums 来记录中序遍历得到的值序列，
     * 然后线性遍历找到两个位置i 和j，并重新遍历原二叉搜索树修改对应节点的值完成修复
     *
     * 方法是显式地将中序遍历的值序列保存在一个nums 数组中，然后再去寻找被错误交换的节点
     *
     * @param root
     */
    public void recoverTree_2(TreeNode root) {
        List<Integer> nums = new ArrayList<Integer>();
        this.inorder(root, nums);

        int[] swapped = findTwoSwapped(nums);
        recover(root, 2, swapped[0], swapped[1]);
    }

    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }

        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public int[] findTwoSwapped(List<Integer> nums) {
        int n = nums.size();
        int x = -1, y = -1;

        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i + 1) < nums.get(i)) {
                y = nums.get(i + 1);

                if (x == -1) {
                    x = nums.get(i);
                } else {
                    break;
                }
            }
        }

        return new int[]{x, y};
    }

    public void recover(TreeNode root, int count, int x, int y) {
        if (root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                if (--count == 0) {
                    return;
                }
            }

            recover(root.right, count, x, y);
            recover(root.left, count, x, y);
        }
    }

}
