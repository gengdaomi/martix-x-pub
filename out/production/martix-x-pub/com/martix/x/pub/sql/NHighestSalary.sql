/**
  第N高的薪水 sql lc 177

  表 Employee
  +-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
Id是该表的主键列。
该表的每一行都包含有关员工工资的信息

编写一个SQL查询来报告 Employee 表中第 n 高的工资。如果没有第 n 个最高工资，查询应该报告为 null 。

查询结果格式如下所示

  输入:
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
n = 2
输出:
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+

输入:
Employee 表:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
n = 2
输出:
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| null                   |
+------------------------+

 */


#由于本题不存在分组排序，只需返回全局第N高的一个，所以自然想到的想法是用order by排序加limit限制得到。
#1.同薪同名且不跳级的问题，解决办法是用group by按薪水分组后再order by；
#2.排名第N高意味着要跳过N-1个薪水，由于无法直接用limit N-1，所以需先在函数开头处理N为N=N-1。

# 注：这里不能直接用limit N-1是因为limit和offset字段后面只接受正整数（意味着0、负数、小数都不行）或者单一变量（意味着不能用表达式），
# 也就是说想取一条，limit 2-1、limit 1.1这类的写法都是报错的

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N := N - 1;

    RETURN (
        # Write your MySQL query statement below.
        SELECT salary
        FROM employee
        GROUP BY salary
        ORDER BY salary DESC
        LIMIT N, 1);
END

;
/*
  第二种方式 子查询

  排名第N的薪水意味着该表中存在N-1个比其更高的薪水
  注意这里的N-1个更高的薪水是指去重后的N-1个，实际对应人数可能不止N-1个
  最后返回的薪水也应该去重，因为可能不止一个薪水排名第N
  由于对于每个薪水的where条件都要执行一遍子查询，注定其效率低下

 */
CREATE FUNCTION getNthHighestSalary_1(N INT) RETURNS INT
BEGIN
    RETURN (
               # Write your MySQL query statement below.
               SELECT
                   DISTINCT e.salary
               FROM
                   employee e
               WHERE
                       (SELECT count(DISTINCT salary) FROM employee WHERE salary>e.salary) = N-1
           );
END
