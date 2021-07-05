package com.martix.x.pub.binary;

/**
 * Created by Andrew-Geng on 11:24 下午 2021/4/19
 * 在 D 天内送达包裹的能力 lc 1011
 * <p>
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * <p>
 * 传送带上的第 i个包裹的重量为weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * <p>
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * 示例 1：
 * <p>
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 * <p>
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * 示例 2：
 * <p>
 * 输入：weights = [3,2,2,4,1,4], D = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * 示例 3：
 * <p>
 * 输入：weights = [1,2,3,1,1], D = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 */
public class ShipWithinDaysSolution {

    public static void main(String[] args) {
        int[] w = new int[]{1,2,3,4,5,6,7,8,9,10};

        System.out.println(new ShipWithinDaysSolution().shipWithinDays(w, 5));
    }

    /**
     * 二分查找的初始左右边界:
     * 对于左边界而言，由于我们不能「拆分」一个包裹，因此船的运载能力不能小于所有包裹中最重的那个的重量，即左边界为数组weights 中元素的最大值;
     * 对于右边界而言，船的运载能力也不会大于所有包裹的重量之和，即右边界为数组 weights 中元素的和
     *
     * 时间复杂度O(NlogN) 空间复杂度O(1)
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int max = 0, sum = 0;
        for (int w : weights) {

            max = Math.max(max, w);
            sum += w;
        }

        int lo = max, hi = sum;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (canShip(weights, D, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean canShip(int[] weights, int D, int K) {
        int cur = K; // cur 表示当前船的可用承载量

        for (int weight: weights) {
            if (weight > K){
                return false;
            }

            if (cur < weight) {
                cur = K;
                D--;
            }
            cur -= weight;
        }
        return D > 0; // 能否在D天内运完
    }
}
