package ots.layim.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    ApplicationContext ac;
    @Before
    public void before(){
         ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }
    @Test
    public void run1(){


    }

}
