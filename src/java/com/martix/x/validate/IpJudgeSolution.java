package com.martix.x.validate;

/**
 * 用两种办法，判断 给定的任意一个字符串，其为真实有效的ip地址？
 *
 * Created By Andrew-Geng on 2020/8/28 8:52 下午
 */
public class IpJudgeSolution {

    public boolean isIp(String ip) {
        if (ip == null || ip == "") {
            return false;
        }

        if (ip.charAt(0) == '.' || ip.charAt(ip.length() - 1) == '.') {
            return false;
        }

        if (ip.length() < 7 || ip.length() > 15) {
            return false;
        }

        String[] ipStrArr = ip.split("\\.");
        if (ipStrArr.length != 4) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < ipStrArr[i].length(); j++) {  //判断字符是否为数字
                if (ipStrArr[i].charAt(j) < '0' || ipStrArr[i].charAt(j) > '9') {
                    return false;
                }
            }
        }

        boolean firstFlag = true; //第一位
        for (int i = 0; i < ipStrArr.length; i++) { //ip范围的校验
            int temp = Integer.parseInt(ipStrArr[i]);

            if (firstFlag && temp == 0) {
                return false;
            }

            if (temp < 0 || temp > 255) {
                return false;
            }

            if (firstFlag) {
                firstFlag = false;
            }
        }

        return true;
    }

    public boolean isIp_2(String ip) {
        if (ip != null && !ip.isEmpty()) {
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

            if (ip.matches(regex)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Boolean flag = new IpJudgeSolution().isIp("0.0.0.0");
        System.out.println(flag);
    }
}
