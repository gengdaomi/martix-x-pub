package com.martix.x.pub.code.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew-Geng on 00:08 2022/9/20
 * 二叉树最大宽度 lc 662
 *
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 *
 * 树的 最大宽度 是所有层中最大的 宽度 。
 *
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 *
 * 题目数据保证答案将会在 32 位 带符号整数范围内。
 *
 *    1
 *  3   2
 * 5 3   9
 *
 输入：root = [1,3,2,5,3,null,9]
 输出：4
 解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。

 1
 3   2
 5     9
 6     7

 输入：root = [1,3,2,5,null,null,9,6,null,7]
 输出：7
 解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。


 1
 3   2
 5

 输入：root = [1,3,2,5]
 输出：2
 解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。

 */
public class MaxTreeWidthSolution {

    /**
     * 深度优先搜索
     *
     *  按照上述方法编号，可以用深度优先搜索来遍历。遍历时如果是先访问左子节点，再访问右子节点，
     *  每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值，需要记录下来进行后续的比较。
     *  一次深度优先搜索中，需要当前节点到当前行最左边节点的宽度，以及对子节点进行深度优先搜索，求出最大宽度，并返回最大宽度。
     *
     * 时间复杂度：O(n)，其中 n 是二叉树的节点个数。需要遍历所有节点。
     * 空间复杂度：O(n)。递归的深度最多为 O(n)。
     * @param root
     * @return
     */
    Map<Integer, Integer> levelMin = new HashMap<Integer, Integer>();

    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 1, 1);
    }

    public int dfs(TreeNode node, int depth, int index) {
        if (node == null) {
            return 0;
        }

        levelMin.putIfAbsent(depth, index); // 每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值

        return Math.max(index - levelMin.get(depth) + 1, Math.max(dfs(node.left, depth + 1, index * 2), dfs(node.right, depth + 1, index * 2 + 1)));
    }

    /**
     * 广度优先搜索
     *
     * 此题求二叉树所有层的最大宽度，比较直观的方法是求出每一层的宽度，然后求出最大值。
     * 求每一层的宽度时，因为两端点间的null 节点也需要计入宽度，因此可以对节点进行编号。
     * 一个编号为index 的左子节点的编号记为2×index，右子节点的编号记为2×index+1，
     * 计算每层宽度时，用每层节点的最大编号减去最小编号再加 1 即为宽度。
     *
     * 遍历节点时，可以用广度优先搜索来遍历每一层的节点，并求出最大值
     *
     * 时间复杂度：O(n)，其中 n 是二叉树的节点个数。需要遍历所有节点。
     * 空间复杂度：O(n)。广度优先搜索的空间复杂度最多为 O(n)。
     * @param root
     * @return
     */
    public int widthOfBinaryTree_1(TreeNode root){
        int result = 1;
        List<Tuple<TreeNode, Integer>> tupleList = new ArrayList<>();

        tupleList.add(new Tuple<>(root, 1));
        while (!tupleList.isEmpty()) {
            List<Tuple<TreeNode, Integer>> tmp = new ArrayList<>();
            for (Tuple<TreeNode, Integer> tuple : tupleList) {
                TreeNode node = tuple.getKey();
                int index = tuple.getValue();
                if (node.left != null) {
                    tmp.add(new Tuple<>(node.left, index * 2));
                }
                if (node.right != null) {
                    tmp.add(new Tuple<>(node.right, index * 2 + 1));
                }
            }

            result = Math.max(result, tupleList.get(tupleList.size() - 1).getValue() - tupleList.get(0).getValue() + 1);
            tupleList = tmp;
        }

        return result;
    }

    /**
     * 二元组类
     * @param <TreeNode>
     * @param <Integer>
     */
    class Tuple<TreeNode,Integer>{
        private TreeNode key;
        private Integer value;

        public Tuple(TreeNode treeNode,Integer value){
            this.key = treeNode;
            this.value = value;
        }

        public TreeNode getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }
    }
}
