/**
  游戏玩法分析 4  sql  lc 550

      Table: Activity

+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
(player_id, event_date) 是这个表的两个主键
这个表显示的是某些游戏玩家的游戏活动情况
每一行是在某天使用某个设备登出之前登录并玩多个游戏（可能为0）的玩家的记录

  编写一个 SQL 查询，报告在首次登录的第二天再次登录的玩家的比率，四舍五入到小数点后两位。
  换句话说，您需要计算从首次登录日期开始至少连续两天登录的玩家的数量，然后除以玩家总数。

  查询结果格式如下所示：
  Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-03-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+

  Result 表：
+----------+
| fraction |
+----------+
| 0.33     |
+----------+

  表示 只有 ID 为 1 的玩家在第一天登录后的第二天才重新登录，所以答案是 1/3 = 0.33
 */

/**
  解法 1  left join +min()
 */
select round(avg(a.event_date is not null), 2) as fraction
from (select player_id, min(event_date) as flogin
      from Activity
      group by player_id) tmp
         left join activity a
                   on tmp.player_id = a.player_id and datediff(a.event_date, tmp.flogin) = 1;

/**
  解法2 date()
 */
select round(count(distinct player_id) /
             (select count(distinct player_id) from activity), 2) as fraction
from activity
where (player_id, event_date) in
      (select player_id, date(min(event_date) + 1)
       from activity
       group by player_id);