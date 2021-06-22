package com.martix.x.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew-Geng on 10:32 下午 2021/5/15
 * 寻找重复的子树 lc 652
 * <p>
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * <p>
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 * <p>
 * 示例 1：
 * <p>
 * 1
 * / \
 * 2   3
 * /   / \
 * 4   2   4
 * /
 * 4
 * 下面是两个重复的子树：
 * <p>
 * 2
 * /
 * 4
 * 和
 * <p>
 * 4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 */
public class FindDuplicateSubtreesSolution {

    private Map<String, Integer> map = new HashMap<>();
    private List<TreeNode> resultList = new ArrayList<>();

    /**
     * 时间复杂度：O(N^2),N 是二叉树上节点的数量。遍历所有节点，在每个节点处序列化需要时间O(N)
     * 空间复杂度：O(N^2)
     *
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        find(root);

        return resultList;
    }

    private String find(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String treeString = Integer.toString(root.val) + ","
                + find(root.left) + find(root.right);

        map.put(treeString, map.getOrDefault(treeString, 0) + 1);
        if (map.get(treeString) == 2) {
            resultList.add(root);
        }

        return treeString;
    }
}
