package com.martix.x.pub.code.arrays;

import java.util.*;

/**
 * Created by Andrew-Geng on 17:48 2022/11/30
 *  扁平化嵌套列表迭代器 lc341
 *
 *  给你一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。
 *
 * 实现扁平迭代器类 NestedIterator ：
 *
 * NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
 * int next() 返回嵌套列表的下一个整数。
 * boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
 * 你的代码将会用下述伪代码检测：
 *
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 *     append iterator.next() to the end of res
 * return res
 * 如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。
 *
 * 示例 1：
 *
 * 输入：nestedList = [[1,1],2,[1,1]]
 * 输出：[1,1,2,1,1]
 * 解释：通过重复调用next 直到hasNext 返回 false，next返回的元素的顺序应该是: [1,1,2,1,1]。
 * 示例 2：
 *
 * 输入：nestedList = [1,[4,[6]]]
 * 输出：[1,4,6]
 * 解释：通过重复调用next直到hasNext 返回 false，next返回的元素的顺序应该是: [1,4,6]。
 *
 */
public class NestIteratorFlattenSolution implements Iterator<Integer>{

    private List<Integer> valList;
    private Iterator<Integer> cur;

    /**
     * 方法1：深度优先搜索
     *
     * 嵌套的整型列表是一个树形结构，树上的叶子节点对应一个整数，非叶节点对应一个列表。
     * 在这棵树上深度优先搜索的顺序就是迭代器遍历的顺序。
     * 我们可以先遍历整个嵌套列表，将所有整数存入一个数组，然后遍历该数组从而实现next 和hasNext 方法
     *
     * 时间复杂度：初始化时O(n)，next和hasNext两个方法为O(1);n为嵌套的整形列表中的元素个数
     * 空间复杂度：O(n),一个数组存储嵌套的整型列表中的所有元素
     */

    public NestIteratorFlattenSolution(List<NestedInteger> nestedList) {
        valList = new ArrayList<Integer>();
        dfs(nestedList);
        cur = valList.iterator();

    }

    @Override
    public Integer next() {
        return cur.next();
    }

    @Override
    public boolean hasNext() {
        return cur.hasNext();
    }

    private void dfs(List<NestedInteger> nestedList) {
        for (NestedInteger nest : nestedList) {
            if (nest.isInteger()) {
                valList.add(nest.getInteger());
            } else {
                dfs(nest.getList());
            }
        }
    }


    /**
     * 方法2  核心思路：栈
     *
     * 用一个栈来代替方法一中的递归过程。
     *
     * 具体来说，用一个栈来维护深度优先搜索时，从根节点到当前节点路径上的所有节点。
     * 由于非叶节点对应的是一个列表，我们在栈中存储的是指向列表当前遍历的元素的指针（下标）。
     * 每次向下搜索时，取出列表的当前指针指向的元素并将其入栈，同时将该指针向后移动一位。如此反复直到找到一个整数。
     * 循环时若栈顶指针指向了列表末尾，则将其从栈顶弹出。
     *
     * 时间复杂度：初始化和next为O(1),和hasNext为O(1)
     *  空间复杂度：O(n),最坏情况下嵌套的整型列表是一条链，我们需要一个O(n)大小的栈来存储链上的所有元素
     */

    // 存储列表的当前遍历位置
    private Deque<Iterator<NestedInteger>> stack;

    public NestIteratorFlattenSolution(List<NestedInteger> nestedList,int i) {
        stack = new LinkedList<Iterator<NestedInteger>>();
        stack.push(nestedList.iterator());
    }

    public Integer next_1() {
        // 由于保证调用 next 之前会调用 hasNext，直接返回栈顶列表的当前元素
        return stack.peek().next().getInteger();
    }

    public boolean hasNext_1() {
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> it = stack.peek();
            if (!it.hasNext()) { // 遍历到当前列表末尾，出栈
                stack.pop();
                continue;
            }

            // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
            NestedInteger nest = it.next();
            if (nest.isInteger()) {
                List<NestedInteger> list = new ArrayList<NestedInteger>();
                list.add(nest);
                stack.push(list.iterator());
                return true;
            }
            stack.push(nest.getList().iterator());
        }
        return false;
    }


    public interface NestedInteger {

              // @return true if this NestedInteger holds a single integer, rather than a nested list.
              public boolean isInteger();

              // @return the single integer that this NestedInteger holds, if it holds a single integer
              // Return null if this NestedInteger holds a nested list
              public Integer getInteger();

              // @return the nested list that this NestedInteger holds, if it holds a nested list
              // Return empty list if this NestedInteger holds a single integer
              public List<NestedInteger> getList();
  }
}
