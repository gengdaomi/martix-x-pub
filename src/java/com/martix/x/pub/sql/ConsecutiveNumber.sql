/**
  连续出现的数字 sql lc 180

  表：Logs
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| num         | varchar |
+-------------+---------+
id 是这个表的主键。

编写一个 SQL 查询，查找所有至少连续出现三次的数字。
返回的结果表中的数据可以按 任意顺序 排列。

查询结果格式如下面的例子所示：

示例 1:

输入：
Logs 表：
+----+-----+
| Id | Num |
+----+-----+
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |
+----+-----+
输出：
Result 表：
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
解释：1 是唯一连续出现至少三次的数字。

 */

 ## 第一种方法 用 DISTINCT 和 WHERE 语句

 /*
     连续出现的意味着相同数字的 Id 是连着的，由于这题问的是至少连续出现 3 次，我们使用 Logs 并检查是否有 3 个连续的相同数字

     然后我们从上表中选择任意的 Num 获得想要的答案。同时我们需要添加关键字 DISTINCT ，因为如果一个数字连续出现超过 3 次，会返回重复元素
  */

SELECT DISTINCT
    log1.num AS ConsecutiveNums
FROM
    Logs as log1,
    Logs as log2,
    Logs as log3
WHERE
        log1.id = log2.id - 1
  AND log2.Id = log3.id - 1
  AND log1.num = log2.num
  AND log2.num = log3.num
