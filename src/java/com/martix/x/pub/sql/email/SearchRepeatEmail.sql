/**
  查找重复的电子邮箱 sql lc 182

  编写一个 SQL 查询，查找Person 表中所有重复的电子邮箱。

示例：

+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
根据以上输入，你的查询应返回以下结果：

+---------+
| Email   |
+---------+
| a@b.com |
+---------+

 */

## 第一种方法 使用 GROUP BY 和临时表

 select distinct Email
     from (
     select Email,count(email) as num
         from Person
         group by Email
           ) as tmp
     where tmp.num>=2;

## 第2种方法  使用 GROUP BY 和 HAVING 条件

select Email
  from Person
     group by Email
    having count(Email) > 1;