package com.martix.x.pub.merge;

import java.util.ArrayList;
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
    }
}
