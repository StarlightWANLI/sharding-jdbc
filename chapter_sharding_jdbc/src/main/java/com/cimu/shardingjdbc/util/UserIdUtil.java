package com.cimu.shardingjdbc.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @program: rwtcpt
 * @description: userId生成器
 * @author: wanli
 * @create: 2019-12-04 16:43
 **/
public class UserIdUtil {
    //范围是0~31  每个id生成器，最好设置不同的值
    private static final  long workerId = 0;
    private static final  long datacenterId = 0;


    /**
     * 获取下一个id
     * @return
     */
    public static long nextId(){
        Snowflake snowflake = IdUtil.getSnowflake(workerId,datacenterId);
        return snowflake.nextId();
    }

/*    public static void main(String[] args){
        for (int i = 0; i < 1000; i++) {
            System.out.println(TaskDetaileIdUtil.nextId());
        }
    }*/

}
