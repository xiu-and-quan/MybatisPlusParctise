package com.example.mptest;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * zookeeper测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ZookeeperTest{

    @Autowired
    CuratorFramework curatorFramework;

    /**
     * 添加持久节点
     * 添加临时节点
     */
    @Test
    public void creatNode() throws Exception {
        //临时序号节点
        String path = curatorFramework.create().forPath("/curator-node");
        System.out.println(String.format("curator create node :%s successfully.",path));

        System.in.read();
    }

    /**
     * 获取数据
     */
    @Test
    public void getDataTest() throws Exception {
        byte[] bytes = curatorFramework.getData().forPath("/curator-node");
        System.out.println(bytes);
    }

    /**
     * 修改
     */
    @Test
    public void setDataTest() throws Exception {
        curatorFramework.setData().forPath("/curator-node","changed!".getBytes());
        byte[] bytes = curatorFramework.getData().forPath("/curator-node");
        System.out.println(bytes);
    }

    /**
     * 创建子节点和父节点
     */
    @Test
    public void createNodeWithParentNodeTest() throws Exception {
        String parentNode = "/parent/child";
        //创建节点，需要的话创建父节点
        curatorFramework.create().creatingParentsIfNeeded().forPath(parentNode);
    }

    /**
     * 创建子节点和父节点
     */
    @Test
    public void deleteNodeWithParentNodeTest() throws Exception {
        String parentNode = "/parent";
        //删除节点，需要的话会删除子节点
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(parentNode);
    }

    /**
     * zookeeper watch机制
     */
    @Test
    public void addNodeListener() throws Exception{
        NodeCache nodeCache = new NodeCache(curatorFramework,"/curator-node");
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                log.info("{} path nodeChanged:","/curator-node");
                printNodeData();
            }
        });
        nodeCache.start();
        System.in.read();
    }

    private void printNodeData() throws Exception{
        byte[] bytes = curatorFramework.getData().forPath("/curator-node");
        log.info("data:{}",new String(bytes));
    }

    /**
     * 读锁
     */
    @Test
    public void ReadLockTest() throws Exception{
        //读写锁
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework,"/lock1");
        //获取读锁对象
        InterProcessLock readLock = interProcessReadWriteLock.readLock();
        System.out.println("等待获取读锁对象");
        //获取锁
        readLock.acquire();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        //释放锁
        readLock.release();
        System.out.println("锁释放");
    }

    /**
     * 写锁
     */
    @Test
    public void WriteLockTest() throws Exception{
        //读写锁
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework,"/lock1");
        //获取读锁对象
        InterProcessLock writeLock = interProcessReadWriteLock.writeLock();
        System.out.println("等待获取写锁对象");
        //获取锁
        writeLock.acquire();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        //释放锁
        writeLock.release();
        System.out.println("锁释放");
    }
}
