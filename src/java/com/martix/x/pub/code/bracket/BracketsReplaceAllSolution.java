package com.martix.x.pub.code.bracket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew-Geng on 18:03 2022/11/30
 * 替换字符串中的括号内容 lc1807
 *
 * 给你一个字符串s，它包含一些括号对，每个括号中包含一个 非空的键。
 *
 * 比方说，字符串"(name)is(age)yearsold"中，有两个括号对，分别包含键"name" 和"age"。
 * 你知道许多键对应的值，这些关系由二维字符串数组knowledge表示，其中knowledge[i] = [keyi, valuei]，表示键keyi对应的值为valuei。
 *
 * 你需要替换 所有的括号对。当你替换一个括号对，且它包含的键为keyi时，你需要：
 *
 * 将keyi和括号用对应的值valuei替换。
 * 如果从 knowledge中无法得知某个键对应的值，你需要将keyi和括号用问号"?"替换（不需要引号）。
 * knowledge中每个键最多只会出现一次。s中不会有嵌套的括号。
 *
 * 请你返回替换 所有括号对后的结果字符串。
 *
 *
 * 示例 1：
 *
 * 输入：s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
 * 输出："bobistwoyearsold"
 * 解释：
 * 键 "name" 对应的值为 "bob" ，所以将 "(name)" 替换为 "bob" 。
 * 键 "age" 对应的值为 "two" ，所以将 "(age)" 替换为 "two" 。
 * 示例 2：
 *
 * 输入：s = "hi(name)", knowledge = [["a","b"]]
 * 输出："hi?"
 * 解释：由于不知道键 "name" 对应的值，所以用 "?" 替换 "(name)" 。
 * 示例 3：
 *
 * 输入：s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
 * 输出："yesyesyesaaa"
 * 解释：相同的键在 s 中可能会出现多次。
 * 键 "a" 对应的值为 "yes" ，所以将所有的 "(a)" 替换为 "yes" 。
 * 注意，不在括号里的 "a" 不需要被替换。
 *
 */
public class BracketsReplaceAllSolution {

    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String,String> map=new HashMap();
        for(List<String> o:knowledge) {
            map.put(o.get(0),o.get(1));
        }

        StringBuilder result=new StringBuilder();
        StringBuilder key=new StringBuilder();
        int keyCount=0;

        for(char c:s.toCharArray()) {
            if(c=='(') {
                keyCount++;//关键字开始

            }else if(c==')') {
                keyCount--;//关键字结束
                result.append(map.getOrDefault(key.toString(), "?"));
                //取出关键字对应的value值 ，没有值就用默认值  ？ 代替
                key=new StringBuilder();//重置关键字

            } else if(keyCount>0) {//关键字进行时

                key.append(c);//关键字成长中
            }else {//非关键字字符，res直接吃掉
                result.append(c);
            }
        }

        return result.toString();
    }
}
