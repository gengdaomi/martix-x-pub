package com.martix.x.pub.code.tree;

import java.util.*;

/**
 * Created by Andrew-Geng on 9:26 下午 2021/5/15
 * 二叉树的序列化与反序列化
 * lc 297  hard
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 */
public class SerializeTreeSolution {

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();
        String s = "[1,2,3,null,null,4,5]";

        String[] str = s.substring(1, s.length() - 1).split(",");
        System.out.println(s);

        Integer d = -1;
        if (!"null".equals(str[3])) {
            d = 10;
        } else {
            d = 99;
        }
        System.out.println(d);

    }

    /**
     * 借用的思路： 层序遍历
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result.toString();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int curLevelSize = queue.size();

            for (int i = 0; i < curLevelSize; i++) {
                TreeNode node = queue.poll();

                if (node == null) {
                    result.add(null);
                } else {
                    result.add(node.val);

                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }

        return result.toString();
    }


    public TreeNode deserialize(String data) {
        if (data == null || data == "[]" || data == "") {
            return null;
        }

        String[] dataStr = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(dataStr[0]));

        Queue<TreeNode> queue = new LinkedList<>(); //队列中存放都是父节点
        queue.offer(root);

        int index = 1; //数组下标 初始值为1，因为已经将root放入队列
        while (index < dataStr.length) {
            TreeNode parent = queue.poll();

            String left = dataStr[index++].trim();
            if (!"null".equals(left)) {
                parent.left = new TreeNode(Integer.valueOf(left));
                queue.offer(parent.left);

            } else {
                parent.left = null;
            }

            String right = dataStr[index++].trim();
            if (!"null".equals(right)) {
                parent.right = new TreeNode(Integer.valueOf(right));
                queue.offer(parent.right);

            } else {
                parent.right = null;
            }
        }

        return root;
    }
}
