/**
  部门工资前三高的所有员工 sql lc 185 hard

  表:Employee

+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |
+--------------+---------+
Id是该表的主键列。
departmentId是Department表中ID的外键。
该表的每一行都表示员工的ID、姓名和工资。它还包含了他们部门的ID。


表:Department

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
Id是该表的主键列。
该表的每一行表示部门ID和部门名。

公司的主管们感兴趣的是公司每个部门中谁赚的钱最多。一个部门的 高收入者 是指一个员工的工资在该部门的 不同 工资中 排名前三 。

编写一个SQL查询，找出每个部门中 收入高的员工 。

以 任意顺序 返回结果表。

查询结果格式如下所示。

示例 1:

输入:
Employee 表:
+----+-------+--------+--------------+
| id | name  | salary | departmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 85000  | 1            |
| 2  | Henry | 80000  | 2            |
| 3  | Sam   | 60000  | 2            |
| 4  | Max   | 90000  | 1            |
| 5  | Janet | 69000  | 1            |
| 6  | Randy | 85000  | 1            |
| 7  | Will  | 70000  | 1            |
+----+-------+--------+--------------+
Department  表:
+----+-------+
| id | name  |
+----+-------+
| 1  | IT    |
| 2  | Sales |
+----+-------+
输出:
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Max      | 90000  |
| IT         | Joe      | 85000  |
| IT         | Randy    | 85000  |
| IT         | Will     | 70000  |
| Sales      | Henry    | 80000  |
| Sales      | Sam      | 60000  |
+------------+----------+--------+
解释:
在IT部门:
- Max的工资最高
- 兰迪和乔都赚取第二高的独特的薪水
- 威尔的薪水是第三高的

在销售部:
- 亨利的工资最高
- 山姆的薪水第二高
- 没有第三高的工资，因为只有两名员工

 */

 ## 第一种解法 使用 JOIN 和子查询
/*
 算法过程中：

 公司里前 3 高的薪水意味着有不超过 3 个工资比这些值大。

   select e1.Name as 'Employee', e1.Salary
         from Employee e1
       where 3 >
    (
    select count(distinct e2.Salary)
    from Employee e2
    where e2.Salary > e1.Salary
    );
在这个代码里，我们统计了有多少人的工资比 e1.Salary 高，所以样例的输出应该如下所示。


| Employee | Salary |
|----------|--------|
| Henry    | 80000  |
| Max      | 90000  |
| Randy    | 85000  |
然后，我们需要把表 Employee 和表 Department 连接来获得部门信息

 */

SELECT
    d.Name AS 'Department', e1.Name AS 'Employee', e1.Salary
FROM
    Employee e1
        JOIN
    Department d ON e1.DepartmentId = d.Id
WHERE
        3 > (SELECT
                 COUNT(DISTINCT e2.Salary)
             FROM
                 Employee e2
             WHERE
                     e2.Salary > e1.Salary
               AND e1.DepartmentId = e2.DepartmentId
    )
;

## 先对Employee表进行部门分组工资排名，再关联Department表查询部门名称，再使用WHERE筛选出排名小于等于3的数据（也就是每个部门排名前3的工资）
/*
   1. rank() over
作用：查出指定条件后的进行排名，条件相同排名相同，排名间断不连续。
说明：例如学生排名，使用这个函数，成绩相同的两名是并列，下一位同学空出所占的名次。即：1 1 3 4 5 5 7

2. dense_rank() over
作用：查出指定条件后的进行排名，条件相同排名相同，排名间断不连续。
说明：和rank() over 的作用相同，区别在于dense_rank() over 排名是密集连续的。
   例如学生排名，使用这个函数，成绩相同的两名是并列，下一位同学接着下一个名次。即：1 1 2 3 4 5 5 6

3. row_number() over
作用：查出指定条件后的进行排名，条件相同排名也不相同，排名间断不连续。
说明：这个函数不需要考虑是否并列，即使根据条件查询出来的数值相同也会进行连续排序。即：1 2 3 4 5 6
 */
SELECT
    B.Name AS Department,
    A.Name AS Employee,
    A.Salary
FROM (SELECT DENSE_RANK() OVER (partition by DepartmentId order by Salary desc) AS ranking,DepartmentId,Name,Salary
      FROM Employee) AS A
         JOIN Department AS B ON A.DepartmentId=B.id
WHERE A.ranking<=3
