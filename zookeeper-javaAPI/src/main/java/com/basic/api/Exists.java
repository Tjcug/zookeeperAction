package com.basic.api;

import com.basic.common.Constant;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * locate com.basic.api
 * Created by mastertj on 2018/5/16.
 * 判断节点是否存在
 */
public class Exists {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        //zkClient 初始化
        ZooKeeper zkClient=null;
        zkClient=new ZooKeeper(Constant.ZK_CONNECT_STRING, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //收到事件后的回调函数（应该是我们自己定义的事件逻辑处理）
                System.out.println(event.getType()+"----"+event.getPath());
            }
        });

        //Stat 为数据节点元数据信息
        Stat exists = zkClient.exists("/TJ", false);
        System.out.println("数据节点/TJ 是否存在: "+exists==null?"false":"true");

        //关闭Zookeeper 客户端
        zkClient.close();
    }
}
