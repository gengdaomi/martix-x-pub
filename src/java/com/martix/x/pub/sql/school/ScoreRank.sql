/**
  分数排名 sql lc 178

  表:Scores

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| score       | decimal |
+-------------+---------+
id是该表的主键。
该表的每一行都包含了一场比赛的分数。Score是一个有两位小数点的浮点值。

编写 SQL 查询对分数进行排序。排名按以下规则计算:

分数应按从高到低排列。
如果两个分数相等，那么两个分数的排名应该相同。
在排名相同的分数后，排名数应该是下一个连续的整数。换句话说，排名之间不应该有空缺的数字。
按score降序返回结果表。

查询结果格式如下所示。

示例 1:

输入:
Scores 表:
+----+-------+
| id | score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+
输出:
+-------+------+
| score | rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+
 */

## 第一种 通过子查询的方式实现
/*
 实现细节：
 1.要实现分数排名 倒序：
   select a.score as Score
         from Scores a
         order by score desc;

 2.计算一个分数X在集合中的排名，即可以认为是：提取出大于等于X的所有分数集合H，将H去重后的元素个数就是X的排名；
    首先获取分数集合H
    select b.Score from Scores b where b.Score >= X;
 2.1 去重集合后就是其排名
    select count(distinct b.score) from Scores b where b.Score >= X as Rank;
 */

select a.score as score ,
       (select count(distinct b.score) from Scores b where b.score >= a.score) as `rank`
        from Scores a
       order by a.score desc ;


## 第二种 使用函数的方式
/**
  1. rank() over
作用：查出指定条件后的进行排名，条件相同排名相同，排名间断不连续。
说明：例如学生排名，使用这个函数，成绩相同的两名是并列，下一位同学空出所占的名次。即：1 1 3 4 5 5 7

2. dense_rank() over
作用：查出指定条件后的进行排名，条件相同排名相同，排名间断不连续。
说明：和rank() over 的作用相同，区别在于dense_rank() over 排名是密集连续的。例如学生排名，使用这个函数，成绩相同的两名是并列，下一位同学接着下一个名次。即：1 1 2 3 4 5 5 6

3. row_number() over
作用：查出指定条件后的进行排名，条件相同排名也不相同，排名间断不连续。
说明：这个函数不需要考虑是否并列，即使根据条件查询出来的数值相同也会进行连续排序。即：1 2 3 4 5 6

使用小提示:
dense_rank() over 后面跟排序的依据的列，下面是用了一个排序好的列(order by score desc)。
注意：如果select中有一列是用rank()这类函数，其他的列都会按着他这列规定好的顺序排

 */

select score, dense_rank() over (order by score desc) as `rank`  #这个rank之所以要加引号，因为rank本身是个函数，直接写rank会报错
        from scores;
