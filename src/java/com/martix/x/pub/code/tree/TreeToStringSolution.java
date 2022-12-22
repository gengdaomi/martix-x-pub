package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 10:51 下午 2021/5/15
 * <p>
 * 根据二叉树创建字符串
 * lc 606
 * <p>
 * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 * <p>
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 二叉树: [1,2,3,4]
 * 1
 * /   \
 * 2     3
 * /
 * 4
 * <p>
 * 输出: "1(2(4))(3)"
 * <p>
 * 解释: 原本将是“1(2(4)())(3())”，
 * 在你省略所有不必要的空括号对之后，
 * 它将是“1(2(4))(3)”。
 * 示例 2:
 * <p>
 * 输入: 二叉树: [1,2,3,null,4]
 * 1
 * /   \
 * 2     3
 * \
 * 4
 * <p>
 * 输出: "1(2()(4))(3)"
 * <p>
 * 解释: 和第一个示例相似，
 * 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
 */
public class TreeToStringSolution {

    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }

        if (root.left == null && root.right == null) {
            return Integer.toString(root.val);
        }

        if (root.right == null) { //只针对右叉树为空的时候 因为
            return Integer.toString(root.val) + "(" + tree2str(root.left) + ")";
        }

        return Integer.toString(root.val) + "(" + tree2str(root.left) + ")"
                + "(" + tree2str(root.right) + ")";
    }
}
