package com.martix.x.tree;

import java.util.*;

/**
 * Created by Andrew-Geng on 11:02 下午 2021/5/15
 * 对称二叉树 lc 101
 * <p>
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 *  
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 *  
 * <p>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 */
public class SymmetricTreeSolution {

    /**
     * 递归的方式
     * 如果同时满足下面的条件，两个树互为镜像:
     * <p>
     * 它们的两个根结点具有相同的值
     * 每个树的右子树都与另一个树的左子树镜像对称
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return checkSymmetric(root, root);
    }

    private boolean checkSymmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        return (node1.val == node2.val) && checkSymmetric(node1.left, node2.right)
                && checkSymmetric(node1.right, node2.left);
    }

    /**
     * 迭代的方式，思路：层级遍历+ 每层节点数据 是否是回文
     *
     * @param root
     * @return
     */
    public boolean isSymmetric_1(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int curLevelSize = queue.size();

            List<String> curLevelData = new ArrayList<>();
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    curLevelData.add(Integer.toString(node.val));

                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    curLevelData.add("#");
                }
            }

            if (!this.isPalindrome(curLevelData)) { //如果当前层不是回文数
                return false;
            }
        }

        return true;
    }

    /**
     * 判断列表是否是回文的
     *
     * @param list
     * @return
     */
    private boolean isPalindrome(List<String> list) {
        int left = 0, right = list.size() - 1;

        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args){
        List<String> result = List.of("3","4","5","3");

        System.out.println(new SymmetricTreeSolution().isPalindrome(result));

    }
}
