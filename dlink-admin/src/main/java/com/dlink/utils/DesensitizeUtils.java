package com.dlink.utils;

import com.dlink.process.exception.DinkyException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DesensitizeUtils {

    /**
     * 获取表达式中${}中的值
     */
    private static final Pattern REGEX = Pattern.compile("\\#\\{([^}]*)\\}");

    public static String replaceEncryptStr(String content) {
        Matcher matcher = REGEX.matcher(content);
        while (matcher.find()) {
            String encryptValue = matcher.group(1);
            String decryptValue;
            try {
                decryptValue = RSAUtils.decryptStr(encryptValue);
            } catch (Exception e) {
                /*
                  注意：TaskController批量提交任务接口被调用时，由于没有提前校验，而可能导致前面的任务已正常提交，后续任务无法继续
                  但源码中似乎本来就没有应对异常的处理逻辑
                 */
                throw new DinkyException("脱敏数据（密码）解析失败");
            }
            content = matcher.replaceFirst(decryptValue);
            matcher = REGEX.matcher(content);
        }

        return content;
    }

    public static void main(String[] args) {
        String content = "CREATE TABLE IF NOT EXISTS dinky_source_test (\n" +
                "    `id` STRING\n" +
                "    ,`gmt_created` TIMESTAMP\n" +
                "    ,`gmt_modified` TIMESTAMP\n" +
                "    ,`status` STRING\n" +
                "    ,PRIMARY KEY ( `id` ) NOT ENFORCED\n" +
                ") WITH (\n" +
                "'connector'='mysql-cdc',\n" +
                "'hostname'='185.185.185',\n" +
                "'port'='3306',\n" +
                "'username'='root',\n" +
                "'password'='#{fBNtCAb8ClH2zG0Dg4D3bl9IBQRt7xUh0nmFnHKFK/zEwmDDKuidBHfI+qU78TDZwTnuSCbGL5kgK1EW0LpHB2N8SuSkcbFcsJuN41+yIDF2zKO9hghoXl84/m1Hx0t1duQjo50akxm+yHuE21fp8z/E83hMjHG4H0rl7VtDgck=}',\n" +
                "'database-name'='snowdream',\n" +
                "'table-name'='dinky_source_test',\n" +
                "'scan.incremental.snapshot.enabled'='true',\n" +
                "'jdbc.properties.characterEncoding'='UTF-8'\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS dinky_sink_test (\n" +
                "    `id` STRING\n" +
                "    ,`gmt_created` TIMESTAMP\n" +
                "    ,`gmt_modified` TIMESTAMP\n" +
                "    ,`status` STRING\n" +
                "    ,PRIMARY KEY ( `id` ) NOT ENFORCED\n" +
                ") WITH (\n" +
                "'connector'='jdbc',\n" +
                "'url'='jdbc:mysql://185.185.185:3306/applecandy?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull&allowPublicKeyRetrieval=true',\n" +
                "'username'='applecandy',\n" +
                "'password'='#{mN3aEI2FIVDSrKAJKBQehVz3RgGVfRvoJksAuhN4K4HN6nmnNRrd0S2wYJ6a58YF7keWW8k5e1izOVjijRUAONKe8VKtFlNDjEIxntkbs/2redvezFgW5I0e1j6WYt76Jhib+bDmySDGr5IMg5lnYg0XHmr3sZ9iDbJfm72iw7I=}',\n" +
                "'table-name'='dinky_sink_test'\n" +
                ");\n" +
                "\n" +
                "insert into dinky_sink_test(id, gmt_created, gmt_modified, status) select id, gmt_created, gmt_modified, 'NORMAL' from dinky_source_test;\n" +
                "\n";

        String result = DesensitizeUtils.replaceEncryptStr(content);
        System.out.println(result);
    }

}

