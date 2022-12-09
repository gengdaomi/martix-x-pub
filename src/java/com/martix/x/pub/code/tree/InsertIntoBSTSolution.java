package com.martix.x.pub.code.tree;

/**
 * Created by Andrew-Geng on 12:10 2022/12/9
 * 二叉搜索树中的插入操作 lc 701
 *
 * 给定二叉搜索树（BST）的根节点root和要插入树中的值value，将值插入二叉搜索树。
 * 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 *
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 *
 * 输入：root = [4,2,7,1,3], val = 5
 * 输出：[4,2,7,1,3,5]
 *
 * 输入：root = [40,20,60,10,30,50,70], val = 25
 * 输出：[40,20,60,10,30,50,70,null,null,25]
 *
 */
public class InsertIntoBSTSolution {

    /**
     * 递归
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        if(val > root.val){
            root.right = insertIntoBST(root.right, val);
        } else{
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }

    /**
     * 迭代
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST_1(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        TreeNode temp = root;

        while (temp != null) {
            if (val < temp.val) {
                if (temp.left == null) {
                    temp.left = new TreeNode(val);
                    break;
                } else {
                    temp = temp.left;
                }
            } else {
                if (temp.right == null) {
                    temp.right = new TreeNode(val);
                    break;
                } else {
                    temp = temp.right;
                }
            }
        }
        return root;
    }

}
