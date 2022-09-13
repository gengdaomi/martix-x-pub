/**
  第二高的薪水 sql lc 176

  编写一个 SQL 查询，获取并返回 Employee 表中第二高的薪水 。如果不存在第二高的薪水，查询应该返回 null

  输入：
Employee 表：
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
输出：
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+


输入：
Employee 表：
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
输出：
+---------------------+
| SecondHighestSalary |
+---------------------+
| null                |
+---------------------+

 */

/*
   解决 “NULL” 问题的另一种方法是使用 “IFNULL” 函数, 使用 IFNULL 和 LIMIT 子句
 */
SELECT
    IFNULL(
            (SELECT DISTINCT Salary
             FROM Employee
             ORDER BY Salary DESC
                LIMIT 1 OFFSET 1),
    NULL) AS SecondHighestSalary;


SELECT
    (SELECT DISTINCT
         Salary
     FROM
         Employee
     ORDER BY Salary DESC
        LIMIT 1 OFFSET 1) AS SecondHighestSalary