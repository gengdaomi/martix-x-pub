package com.martix.x.pub.merge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ayue on 下午3:07 2018/7/6
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * <p>
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * <p>
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * <p>
 * 这道题目 和 RangeMergeSolution的解题思路一致 可以将 用作判断的newInterval加入到原来的list中，重新按照每个区间的起点排序
 */
public class RangeInsertSolution {

    public static void main(String[] args) {
        Interval i = new Interval(1, 3);
        Interval j = new Interval(6, 9);

        List<Interval> list = Stream.of(i, j).collect(Collectors.toList());

        System.out.println(new RangeInsertSolution().insert(list, new Interval(2, 5)));

    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();

        if (intervals == null || intervals.size() == 0) {
            if (newInterval == null) {
                return result;
            } else {
                result.add(newInterval);
                return result;
            }
        }

        if (newInterval == null) {
            return intervals;
        }

        intervals.add(newInterval);

        List<Interval> intervalList = intervals.stream()
                .sorted(Comparator.comparing(interval -> interval.start))
                .collect(Collectors.toList());

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


}
