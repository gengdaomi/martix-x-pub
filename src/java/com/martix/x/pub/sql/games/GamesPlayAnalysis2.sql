/**
  游戏玩法分析 2  sql  lc 512

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
请编写一个 SQL 查询，描述每一个玩家首次登陆的设备名称

查询结果格式在以下示例中：

Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+

Result table:
+-----------+-----------+
| player_id | device_id |
+-----------+-----------+
| 1         | 2         |
| 2         | 3         |
| 3         | 1         |
+-----------+-----------+

 */

/*
 第一种解法

 窗口函数
 1.ROW_NUMBER()函数：按顺序求行数，结果为1，2，3，4
 2.RANK()函数：有间隔的分级，结果为1，2，2，4
 3.DENSE_RANK()函数：无间隔的分级，结果为1，2，2，3

 1.PARTITON BY 是用来分组，即选择要看哪个窗口，类似于 GROUP BY 子句的分组功能，
   但是 PARTITION BY 子句并不具备 GROUP BY 子句的汇总功能，并不会改变原始表中记录的行数。
 2.ORDER BY 是用来排序，即决定窗口内，是按那种规则(字段)来排序的。
 */
SELECT player_id,device_id
FROM(
        SELECT player_id, device_id,
               ROW_NUMBER() OVER (PARTITION BY player_id
                   ORDER BY event_date) AS ranknum
        FROM Activity)as a
WHERE ranknum = 1;

/**
  第二种解法
  子查询

 1.首先使用min函数和group by找到所有玩家初次在平台使用设备玩游戏的时间
 2.将步骤1的结果作为子查询，外层再套用一层，查找player_id和device_id即可
 */
SELECT DISTINCT player_id, device_id
FROM Activity
WHERE (play_id,event_date) IN
      (SELECT play_id,MIN(event_date)
       FROM Activity
       GROUP BY play_id )
