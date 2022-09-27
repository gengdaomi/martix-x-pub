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

SELECT
    DISTINCT log1.num AS ConsecutiveNums
FROM
    Logs as log1,
    Logs as log2,
    Logs as log3
WHERE
      log1.id = log2.id - 1
  AND log2.id = log3.id - 1
  AND log1.num = log2.num
  AND log2.num = log3.num


/*
  第2种解法：

    #初始化两个变量，一个为pre，记录上一个数字；一个为count，记录上一个数字已经连续出现的次数。
    #然后调用if()函数，如果pre和当前行数字相同，count加1极为连续出现的次数；如果不同，意味着重新开始一个数字，count重新从1开始。
    #最后，将当前的Num数字赋值给pre，开始下一行扫描。

    #将上面表的结果中，重复次数大于等于3的数字选出，再去重即为连续至少出现三次的数字。
    #注意：pre初始值最好不要赋值为一个数字，因为不确定赋值的数字是否会出现在测试表中。
 */
select
    distinct Num as ConsecutiveNums
from
    (select Num,
            if(@pre=Num,@count := @count+1,@count := 1) as nums,
            @pre:=Num
     from Logs as l ,
          (select @pre:= null,@count:=1) as pc
    ) as n
where nums >=3;
