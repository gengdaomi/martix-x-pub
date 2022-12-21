package com.martix.x.pub.code.design;

import java.util.*;

/**
 * Created by Andrew-Geng on 21:19 2022/12/20
 * 推文计数 lc1348
 *
 * 一家社交媒体公司正试图通过分析特定时间段内出现的推文数量来监控其网站上的活动。这些时间段可以根据特定的频率（ 每分钟 、每小时 或 每一天 ）划分为更小的 时间段 。
 *
 *
 * 例如，周期 [10,10000]（以 秒 为单位）将被划分为以下频率的 时间块 :
 *
 * 每 分钟 (60秒 块)：[10,69],[70,129],[130,189],...,[9970,10000]
 * 每 小时 (3600秒 块)：[10,3609],[3610,7209],[7210,10000]
 * 每 天 (86400秒 块)：[10,10000]
 * 注意，最后一个块可能比指定频率的块大小更短，并且总是以时间段的结束时间结束(在上面的示例中为 10000 )。
 *
 * 设计和实现一个API来帮助公司进行分析。
 *
 * 实现 TweetCounts 类:
 *
 * TweetCounts() 初始化 TweetCounts 对象。
 * 存储记录时间的 tweetName (以秒为单位)。
 * List<integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) 返回一个整数列表，表示给定时间 [startTime, endTime] （单位秒）和频率频率中，每个 时间块 中带有 tweetName 的 tweet 的数量。
 * freq 是 “minute” 、 “hour” 或 “day” 中的一个，分别表示 每分钟 、 每小时 或 每一天 的频率。
 *  
 *
 * 示例：
 *
 * 输入：
 * ["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
 * [[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]
 *
 * 输出：
 * [null,null,null,null,[2],[2,1],null,[4]]
 *
 * 解释：
 * TweetCounts tweetCounts = new TweetCounts();
 * tweetCounts.recordTweet("tweet3", 0);
 * tweetCounts.recordTweet("tweet3", 60);
 * tweetCounts.recordTweet("tweet3", 10);                             // "tweet3" 发布推文的时间分别是 0, 10 和 60 。
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // 返回 [2]。统计频率是每分钟（60 秒），因此只有一个有效时间间隔 [0,60> - > 2 条推文。
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // 返回 [2,1]。统计频率是每分钟（60 秒），因此有两个有效时间间隔 1) [0,60> - > 2 条推文，和 2) [60,61> - > 1 条推文。
 * tweetCounts.recordTweet("tweet3", 120);                            // "tweet3" 发布推文的时间分别是 0, 10, 60 和 120 。
 * tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // 返回 [4]。统计频率是每小时（3600 秒），因此只有一个有效时间间隔 [0,211> - > 4 条推文。
 *
 */
public class TweetCountsSolution {

    // key 用户->推文时间->该时间推文发布数目
    private Map<String, TreeMap<Integer,Integer>> userTweetMap;

    /**
     * 核心思路：
     *
     * 1.用一个map来记录用户发布的全部推文
     * 2.推文发布存在时间的先后顺序，这时候可以想到treeMap是一个天生有序的集合
     * 3.这里为什么用treeMap而不是treeSet呢，因为会存在一个用户在同一个时间多次调用recordTweet，
     * 这样推文的数目就不是简单的1个，而是会有多个的存在，于是再用一个空间，在存储在time的时间，发布了多少条推特。
     *
     * 用TreeMap比用List时间复杂度更低一些，因为用TreeMap查找记录，直接通过二分查找定位，这一步节省了很多时间，定位到开始的位置之后，接着就是迭代查找后继了（这明明就是跳表呀）。
     */
    public TweetCountsSolution() {
        userTweetMap = new HashMap<>();
    }

    // 发布推文
    public void recordTweet(String tweetName, int time) {
        // 当前用户推文集合
        TreeMap<Integer, Integer> tweetMap = userTweetMap.computeIfAbsent(tweetName, k -> new TreeMap<>());
        // 推文时间记录，比之前次数多1
        tweetMap.put(time,tweetMap.getOrDefault(time,0) + 1);// 推文加入
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> result = new ArrayList<>();
        if (!userTweetMap.containsKey(tweetName)){
            result.add(0);
            return result;
        }

        int freqTime = 1;
        if ("minute".equals(freq)){
            freqTime = 60;
        }else if ("hour".equals(freq)){
            freqTime = 3600;
        }else if ("day".equals(freq)){
            freqTime = 86400;
        }

        // 用户的全部推文时间集合
        TreeMap<Integer,Integer> tweetMap = userTweetMap.get(tweetName);
        int start = startTime;
        int end = Math.min(start + freqTime,endTime + 1);
        while (start <= endTime){
            int count = 0;
            // 找到发文时间大于等于start的推文
            Map.Entry<Integer,Integer> entry = tweetMap.ceilingEntry(start);

            while (entry != null && entry.getKey() < end){
                count += entry.getValue();// 推文数累加
                // 找比当前大的推文时间
                entry = tweetMap.higherEntry(entry.getKey());
            }

            result.add(count);
            // 时间后移
            start = end;
            end = Math.min(end + freqTime,endTime + 1);
        }
        return result;
    }
}
