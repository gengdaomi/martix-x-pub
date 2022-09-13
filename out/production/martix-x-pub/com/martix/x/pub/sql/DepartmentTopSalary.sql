/*
 部门工资最高的员工 sql lc 184

 表：Employee

+--------------+---------+
| 列名          | 类型    |
+--------------+---------+
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |
+--------------+---------+
id是此表的主键列。
departmentId是Department表中ID的外键。
此表的每一行都表示员工的ID、姓名和工资。它还包含他们所在部门的ID。

表：Department

+-------------+---------+
| 列名         | 类型    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
id是此表的主键列。
此表的每一行都表示一个部门的ID及其名称。

编写SQL查询以查找每个部门中薪资最高的员工。
按 任意顺序 返回结果表。
查询结果格式如下例所示。

 输入：
Employee 表:
+----+-------+--------+--------------+
| id | name  | salary | departmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 70000  | 1            |
| 2  | Jim   | 90000  | 1            |
| 3  | Henry | 80000  | 2            |
| 4  | Sam   | 60000  | 2            |
| 5  | Max   | 90000  | 1            |
+----+-------+--------+--------------+
Department 表:
+----+-------+
| id | name  |
+----+-------+
| 1  | IT    |
| 2  | Sales |
+----+-------+
输出：
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Jim      | 90000  |
| Sales      | Henry    | 80000  |
| IT         | Max      | 90000  |
+------------+----------+--------+
解释：Max 和 Jim 在 IT 部门的工资都是最高的，Henry 在销售部的工资最高。

 */

#使用 JOIN 和 IN 语句
/*
  核心算法：
  因为 Employee 表包含 Salary 和 DepartmentId 字段，我们可以以此在部门内查询最高工资
  SELECT
    DepartmentId, MAX(Salary)
FROM
    Employee
GROUP BY DepartmentId;

  | DepartmentId | MAX(Salary) |
|--------------|-------------|
| 1            | 90000       |
| 2            | 80000       |

  可以把表 Employee 和 Department 连接，再在这张临时表里用 IN 语句查询部门名字和工资的关系。
 */

SELECT
    Department.name AS 'Department',
    Employee.name AS 'Employee',
    Salary
FROM
    Employee JOIN Department
             ON Employee.DepartmentId = Department.Id
WHERE
        (Employee.DepartmentId , Salary) IN
        (   SELECT
                DepartmentId, MAX(Salary)
            FROM
                Employee
            GROUP BY DepartmentId
        )
;