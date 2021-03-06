package com.basic.recipes.distributedBarrier;
import com.basic.recipes.common.Constant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;
//使用Curator实现分布式Barrier
public class Recipes_Barrier {
	static String barrier_path = "/curator_recipes_barrier_path";
	static DistributedBarrier barrier;
	public static void main(String[] args) throws Exception {
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString(Constant.ZK_CONNECT_STRING)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		client.start();
		barrier = new DistributedBarrier(client, barrier_path);
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + "号barrier设置" );
						barrier.setBarrier();
						barrier.waitOnBarrier();
						System.err.println("启动...");
					} catch (Exception e) {}
				}
			}).start();
		}
		Thread.sleep( 5000 );
		barrier.removeBarrier();
	}
}
