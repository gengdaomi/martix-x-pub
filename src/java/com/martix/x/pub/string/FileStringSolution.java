package com.martix.x.pub.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * 题目2 给一个包含1000w字符串的文件，写个程序统计下文件中每个字符串出现的次数，并且按字符串出现的次数排序
 * Created By Andrew-Geng on 2020/6/4 8:17 下午
 */
public class FileStringSolution {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        File txtFile = new File("/Users/andrew-geng/Tools/test.txt");
        FileReader reader = new FileReader(txtFile);
        BufferedReader bReader = new BufferedReader(reader);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletionService<Map<String, Integer>> completionService = new ExecutorCompletionService<Map<String, Integer>>(executor);

        String s = "";
        int lineNum = 0; //按多少行划分
        int batchNum = 0; //分的批次数
        List<String> batchStrList = new ArrayList<>();

        while ((s = bReader.readLine()) != null) {
            batchStrList.add(s);
            lineNum++;

            if (lineNum % 100 == 0) {
                batchNum++;
                completionService.submit(new MinBatchCallable(batchStrList));
                batchStrList = new ArrayList<>();
            }
        }

        if (batchStrList.size() != 0) {
            batchNum++;
            completionService.submit(new MinBatchCallable(batchStrList));
        }

        executor.shutdown();

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < batchNum; i++) {
            Map<String, Integer> subMap = completionService.take().get();

            for (Map.Entry entry : subMap.entrySet()) {
                if (map.containsKey(entry.getKey())) {
                    map.put(entry.getKey().toString(), map.get(entry.getKey()) + (Integer) entry.getValue());
                } else {
                    map.put(entry.getKey().toString(), (Integer) entry.getValue());
                }
            }
        }

        bReader.close();

        List<StringNode> result = new FileStringSolution().sort(map);
        System.out.println(result);
    }

    /**
     * 小批量处理任务
     */
    static class MinBatchCallable implements Callable {
        List<String> batchStrList; //批量行

        public MinBatchCallable(List<String> batchStrList) {
            this.batchStrList = batchStrList;
        }


        @Override
        public Map<String, Integer> call() throws Exception {
            Map<String, Integer> map = new HashMap<>();

            for (String strList : batchStrList) {
                if (strList != null && strList.trim().length() > 0) {  //这个把每行字符串 按空格分隔分开 获取
                    String[] strArr = strList.split(" ");

                    for (int i = 0; i < strArr.length; i++) {
                        map.put(strArr[i], map.get(strArr[i]) == null ? 1 : map.get(strArr[i]) + 1);
                    }
                }
            }

            return map;
        }
    }

    /**
     * 基于map数据转换为有序链表  倒序
     *
     * @param map
     * @return
     */
    public List<StringNode> sort(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> keyList = new ArrayList<>(map.entrySet()); //初始化

        Collections.sort(keyList, (o1, o2) -> {
            if (o2.getValue().compareTo(o1.getValue()) < 0) {
                return -1;
            } else if (o2.getValue().compareTo(o1.getValue()) > 0) {
                return 1;
            } else {
                return 0;
            }
        });

        List<StringNode> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : keyList) {
            StringNode stringNode = new StringNode(entry.getKey(), entry.getValue());
            result.add(stringNode);
        }

        return result;
    }

    class StringNode {
        int count;
        String val;

        public StringNode(String val, int count) {
            this.count = count;
            this.val = val;
        }

        @Override
        public String toString() {
            return "StringNode{" +
                    "count=" + count +
                    ", val='" + val + '\'' +
                    '}';
        }
    }
}
