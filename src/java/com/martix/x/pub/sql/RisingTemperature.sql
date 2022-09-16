/**
  上升的温度 sql lc197
  表：Weather

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| recordDate    | date    |
| temperature   | int     |
+---------------+---------+
id 是这个表的主键
该表包含特定日期的温度信息

编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 id 。

返回结果 不要求顺序 。

查询结果格式如下例。

示例 1：

输入：
Weather 表：
+----+------------+-------------+
| id | recordDate | Temperature |
+----+------------+-------------+
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |
+----+------------+-------------+
输出：
+----+
| id |
+----+
| 2  |
| 4  |
+----+
解释：
2015-01-02 的温度比前一天高（10 -> 25）
2015-01-04 的温度比前一天高（20 -> 30）
 */

/**
  解法1 用 to_days()函数
  函数 to_days( ) ：返回从0000年（公元1年）至 当前日期（所给参数）的总天数
 */
 select w1.id
       from Weather as w1, Weather as w2
       where w1.temperature>w2.temperature and to_days(w1.recordDate)-to_days(w2.recordDate)=1;

/**
  解法2 先用datediff() 函数，
  函数datediff( ) : 返回两个日期之间的天数
 */
select w1.id
from Weather as w1, Weather as w2
where w1.temperature>w2.temperature and datediff(w1.recordDate,w2.recordDate)=1