/**
 超过5名学生的课

  表：Courses

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| student     | varchar |
| class       | varchar |
+-------------+---------+
(student, class)是该表的主键列。
该表的每一行表示学生的名字和他们注册的班级。

编写一个SQL查询来报告 至少有5个学生 的所有班级。

以 任意顺序 返回结果表。

查询结果格式如下所示。

示例 1:

输入:
Courses table:
+---------+----------+
| student | class    |
+---------+----------+
| A       | Math     |
| B       | English  |
| C       | Math     |
| D       | Biology  |
| E       | Math     |
| F       | Computer |
| G       | Math     |
| H       | Math     |
| I       | Math     |
+---------+----------+
输出:
+---------+
| class  |
+---------+
| Math  |
+---------+
解释:
-数学课有6个学生，所以我们包括它。
-英语课有1名学生，所以我们不包括它。
-生物课有1名学生，所以我们不包括它。
-计算机课有1个学生，所以我们不包括它。
 */

 ## 第一种解法
/*
  使用 GROUP BY 子句和子查询

  1. 使用 GROUP BY 和 COUNT 获得每门课程的学生数量;

  SELECT
    class, COUNT(DISTINCT student)
FROM
    courses
GROUP BY class

  2.使用上面查询结果的临时表进行子查询，筛选学生数量超过 5 的课程
 */
 select class
       from (
                SELECT
                    class, COUNT(DISTINCT student) as nums
                FROM
                    courses
                GROUP BY class
            ) as tmp
       where tmp.nums >=5;

## 第二种解法
/**
    使用 GROUP BY 和 HAVING 条件
 */
select class
   from courses
   group by class
   having count(distinct student)>=5;