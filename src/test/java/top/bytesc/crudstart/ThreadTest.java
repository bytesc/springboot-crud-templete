package top.bytesc.crudstart;

import org.junit.jupiter.api.Test;

public class ThreadTest {
    @Test
    public void testThreadLocal(){
        ThreadLocal tl = new ThreadLocal();

        new Thread(()-> {
            tl.set("name1");
            System.out.println(Thread.currentThread().getName()+tl.get());
        },"t1"
        ).start();

        new Thread(()-> {
            tl.set("name2");
            System.out.println(Thread.currentThread().getName()+tl.get());
        },"t2"
        ).start();
    }
}
