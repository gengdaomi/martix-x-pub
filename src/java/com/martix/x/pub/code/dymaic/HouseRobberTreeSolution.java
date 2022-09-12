package com.martix.x.pub.code.dymaic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew-Geng on 10:38 下午 2021/7/19
 * 打家劫舍 III lc 337
 * <p>
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * <p>
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * 输出: 7
 * 解释:小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 * <p>
 * 输入: [3,4,5,1,3,null,1]
 * <p>
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * <p>
 * 输出: 9
 * 解释:小偷一晚能够盗取的最高金额= 4 + 5 = 9.
 */
public class HouseRobberTreeSolution {

    Map<TreeNode, Integer> selectedMap = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> unSelectedMap = new HashMap<TreeNode, Integer>();


    /**
     * 动态规划
     * 简化题目：
     * 一棵二叉树，树上的每个点都有对应的权值，每个点有两种状态（选中和不选中），问在不能同时选中有父子关系的点的情况下，能选中的点的最大权值和是多少。
     *
     * 思路:
     * 1.selectedMap用(x) 表示选择x节点的情况下, x节点的子树上被选择的节点的最大权值和；
     * 2.unSelectedMap(x) 表示不选择x节点的情况下,x节点的子树上被选择的节点的最大权值和；l 和r 代表x的左右孩子。
     *
     * 1.当x被选中时，x的左右孩子都不能被选中，故x被选中情况下子树上被选中点的最大权值和为l 和r 不被选中的最大权值和相加，即selectedMap(x)=unSelectedMap(l)+unSelectedMap(r)。
     * 2.当x不被选中时，x的左右孩子可以被选中，也可以不被选中。对于x的某个具体的孩子node，它对x的贡献是node 被选中和不被选中情况下权值和的较大值。
     * 故  unSelectedMap(o=x)=max{selectedMap(l),unSelectedMap(l)}+max{selectedMap(r),unSelectedMap(r)}。
     *
     *最终，
     * 我们可以用哈希表来存selectedMap和unSelectedMap的函数值，用深度优先搜索的办法后序遍历这棵二叉树，我们就可以得到每一个节点的
     * selectedMap和unSelectedMap。根节点的selectedMap和unSelectedMap的最大值就是我们要找的答案。
     *
     * 时间空间复杂度O(n)
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(selectedMap.getOrDefault(root, 0), unSelectedMap.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        selectedMap.put(node, node.val + unSelectedMap.getOrDefault(node.left, 0) + unSelectedMap.getOrDefault(node.right, 0));

        unSelectedMap.put(node, Math.max(selectedMap.getOrDefault(node.left, 0), unSelectedMap.getOrDefault(node.left, 0))
                + Math.max(selectedMap.getOrDefault(node.right, 0), unSelectedMap.getOrDefault(node.right, 0)));
    }


    /**
     * 做一个小小的优化，我们发现无论是f(o) 还是g(o)，他们最终的值只和f(l)、g(l)、f(r)、g(r) 有关，所以对于每个节点，
     * 我们只关心它的孩子节点们的f 和g 是多少。
     * 我们可以设计一个结构，表示某个节点的f 和g 值，在每次递归返回的时候，都把这个点对应的f 和g 返回给上一级调用，这样可以省去哈希表的空间。
     *
     * @param root
     * @return
     */
    public int rob_1(TreeNode root) {
        int[] rootStatus = dfs_1(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs_1(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs_1(node.left);
        int[] r = dfs_1(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
