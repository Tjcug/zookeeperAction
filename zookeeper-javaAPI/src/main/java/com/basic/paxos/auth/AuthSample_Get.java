package com.basic.paxos.auth;
import com.basic.common.Constant;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
//使用无权限信息的ZooKeeper会话访问含权限信息的数据节点
public class AuthSample_Get {

    final static String PATH = "/zk-book-auth_test";
    public static void main(String[] args) throws Exception {

        ZooKeeper zookeeper1 = new ZooKeeper(Constant.ZK_CONNECT_STRING,Constant.ZK_SESSION_TIMEOUT,null);
        zookeeper1.addAuthInfo("digest", "foo:true".getBytes());
        zookeeper1.create( PATH, "init".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL );
        
        ZooKeeper zookeeper2 = new ZooKeeper(Constant.ZK_CONNECT_STRING,Constant.ZK_SESSION_TIMEOUT,null);
        zookeeper2.getData( PATH, false, null );
    }
}
