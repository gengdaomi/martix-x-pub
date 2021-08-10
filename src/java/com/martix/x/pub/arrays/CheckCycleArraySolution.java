package com.martix.x.pub.arrays;

/**
 * Created by Andrew-Geng on 12:17 上午 2021/8/3
 * <p>
 * 环形数组是否存在循环
 * lc 457
 * <p>
 * 存在一个不含 0 的 环形 数组 nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
 * <p>
 * 如果 nums[i] 是正数，向前 移动 nums[i] 步
 * 如果 nums[i] 是负数，向后 移动 nums[i] 步
 * 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
 * <p>
 * 数组中的 循环 由长度为 k 的下标序列 seq ：
 * <p>
 * 遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * 所有 nums[seq[j]] 应当不是 全正 就是 全负
 * k > 1
 * 如果 nums 中存在循环，返回 true ；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,-1,1,2,2]
 * 输出：true
 * 解释：存在循环，按下标 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 * <p>
 * 输入：nums = [-1,2]
 * 输出：false
 * 解释：按下标 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
 */
public class CheckCycleArraySolution {

    public boolean circularArrayLoop(int[] nums) {
        boolean hasCircle = false;
        int length = nums.length;

        //简单类型的判断，长度小于2，必错
        if (length == 1 || length == 0) {
            return false;
        }

        //简单类型判断，长度为2，一正一负必错
        if (length == 2 && (nums[0] * nums[1] <= 0)) {
            return hasCircle;
        }

        //做数组的深拷贝，标记出现在循环链路中的数值
        //数值未构成循环，必然不会在最终循环结果出现
        int[] nums2 = new int[length];
        for (int a = 0; a < length; a++) {
            nums2[a] = nums[a];
        }

        //遍历数组，进行本次循环判断
        for (int b = 0; b < length; b++) {
            if (nums2[b] == 0) {
                continue;
            } else {
                int nownum = nums[b];
                int nowIndex = b;
                nums2[b] = 0;
                int circleLength = 0;

                while (true) {
                    //获取下一个元素索引及值
                    int nextIndex = (nowIndex + nownum);
                    if (nextIndex >= 0) {
                        nextIndex = nextIndex % length;
                    } else {
                        nextIndex = length - ((0 - nextIndex) % length);
                    }

                    int nextNum = nums[nextIndex];
                    //下个元素和当前元素相同，循环长度为1
                    //下个元素和当前元素一正一负，循环方向不一致
                    if (nextIndex == nowIndex || nownum * nextNum < 0) {
                        break;
                    }

                    //完成一轮循环
                    if (circleLength > length) {
                        return true;
                    }

                    circleLength++;
                    nums2[nextIndex] = 0;
                    nownum = nextNum;
                    nowIndex = nextIndex;
                }
            }
        }

        return hasCircle;
    }


    /**
     * -===========第二道=====
     */

    //总体访问过节点
    boolean[] sign;
    public boolean circularArrayLoop_1(int[] nums) {
        int n = nums.length;
        //n = 1 时 ，直接违背（k > 1），返回false
        if(n == 1) return false;
        //全局访问数组，记录全局访问过的节点
        sign = new boolean[n];
        for(int i = 0 ; i < n ; i++){
            //若该节点已经在全局数组中记录，则包含该节点的路径都不可能成环
            if(sign[i]) continue;
            //单线访问数组，记录每次以新的节点为起点时访问的节点
            boolean[] bl = new boolean[n];
            if(nums[i] < 0){
                if(dfs2(i , nums , n , i , bl)) return true;
            }else{
                if(dfs1(i , nums , n , i , bl)) return true;
            }
        }
        return false;

    }

    //大于0时走的路径
    public boolean dfs1(int index , int[] nums , int n , int pre , boolean[] bl){
        //计算出正确下标
        if(index >= n) index %= n;
        //若走到点的值小于0，则违背题意（所有 nums[seq[j]] 应当不是 全正 就是 全负），返回false
        if(nums[index] < 0) return false;
        //若在单线中访问到之前访问过的节点，进入判断
        if(bl[index]){
            //是否为自己访问自己，若是，返回false（k > 1）
            if(index == pre) return false;
            //若不是，返回true
            return true;
        }
        if(sign[index]) return false;
        //全局访问过点保存
        sign[index] = true;
        //单线访问点保存
        bl[index] = true;
        //递归访问下一节点
        return dfs1(index + nums[index] , nums , n , index , bl);
    }

    //小于0时走的路径
    public boolean dfs2(int index , int[] nums , int n , int pre , boolean[] bl){
        //计算出正确下标
        if(index < 0) index = n - ((-index) % n);
        //若走到点的值大于0，则违背题意（所有 nums[seq[j]] 应当不是 全正 就是 全负），返回false
        if(nums[index] > 0) return false;
        //若在单线中访问到之前访问过的节点，进入判断
        if(bl[index]){
            //是否为自己访问自己，若是，返回false（k > 1）
            if(index == pre) return false;
            //若不是，返回true
            return true;
        }
        if(sign[index]) return false;
        //全局访问过点保存
        sign[index] = true;
        //单线访问点保存
        bl[index] = true;
        //递归访问下一节点
        return dfs2(index + nums[index] , nums , n , index , bl);
    }


}
