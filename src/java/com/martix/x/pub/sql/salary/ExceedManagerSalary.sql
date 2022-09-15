/**
  超过经理收入的员工 sql lc 181

表：Employee

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| salary      | int     |
| managerId   | int     |
+-------------+---------+
Id是该表的主键。
该表的每一行都表示雇员的ID、姓名、工资和经理的ID。

编写一个SQL查询来查找收入比经理高的员工。
以 任意顺序 返回结果表。
查询结果格式如下所示。

示例 1:

输入:
Employee 表:
+----+-------+--------+-----------+
| id | name  | salary | managerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | Null      |
| 4  | Max   | 90000  | Null      |
+----+-------+--------+-----------+
输出:
+----------+
| Employee |
+----------+
| Joe      |
+----------+
解释: Joe 是唯一挣得比经理多的雇员。

 */

 ## 第一种解法

select staff.name as Employee
     from Employee as staff, Employee as manager
     where staff.managerId=manager.id and staff.salary>manager.salary;

# 第二种解法 join的形式

select a.name as Employee
     from Employee as a join Employee as b
          on a.managerId=b.id and a.salary>b.salary;