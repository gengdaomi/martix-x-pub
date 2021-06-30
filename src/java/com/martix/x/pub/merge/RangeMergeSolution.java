package com.martix.x.pub.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ayue on 下午5:40 2018/6/27
 * <p>
 * 给出一个区间的集合，请合并所有重叠的区间
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class RangeMergeSolution {

    /**
     * 按照左端点进行排序，那么在排完序的列表中，可以合并的区间一定是连续的
     *
     * 我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间:
     *
     *1.如果当前区间的左端点在数组result中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组result末尾；
     *2.否则，它们重合，我们需要用当前区间的右端点更新数组result中最后一个区间的右端点，将其置为二者的较大值
     *
     * 时间 空间复杂度O(logn)
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, (interval1, interval2) -> interval1[0] - interval2[0]);

        List<int[]> result = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int left = intervals[i][0], right = intervals[i][1];

            if (result.size() == 0 || result.get(result.size() - 1)[1] < left) {
                result.add(new int[]{left, right});
            } else {
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], right);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    /**
     * 按照左端点进行排序，那么在排完序的列表中，可以合并的区间一定是连续的
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<>();
        }

        if (intervals.size() == 1) {
            return intervals;
        }

        List<Interval> intervalList = intervals.stream()
                .sorted(Comparator.comparing(interval -> interval.start))
                .collect(Collectors.toList());

        List<Interval> result = new ArrayList<>();

        Interval tmp = new Interval(intervalList.get(0).start, intervalList.get(0).end);
        int i = 1;
        while (i < intervalList.size()) {
            if (tmp.end >= intervalList.get(i).start) {
                if (tmp.end <= intervalList.get(i).end) {
                    tmp.end = intervalList.get(i).end;

                    if (tmp.start > intervalList.get(i).start) {
                        tmp.start = intervalList.get(i).start;
                    }

                    i++;
                    continue;
                }

                if (tmp.end > intervalList.get(i).end) {
                    if (tmp.start > intervalList.get(i).start) {
                        tmp.start = intervalList.get(i).start;
                    }

                    i++;
                    continue;
                }
            } else {
                result.add(tmp);

                tmp = new Interval(intervalList.get(i).start, intervalList.get(i).end);
                i++;
            }
        }

        result.add(tmp);

        return result;
    }

    /**
     * 创建一个子区间的对象
     */
    private static class Interval {
        int start;
        int end;

        public Interval() {
            this.start = 0;
            this.end = 0;
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) {
        Interval i = new Interval(1, 5);
        Interval j = new Interval(6, 9);

        List<Interval> list = Stream.of(i, j).collect(Collectors.toList());

        System.out.println(new RangeMergeSolution().merge(list));

        int[][] arr =new int[][]{{1,3},{2,6},{8,10},{15,18}};
       int[][] result = new RangeMergeSolution().merge(arr);
       System.out.println(result);
    }
}
