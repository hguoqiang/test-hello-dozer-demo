package org.example;

import org.example.demo.StudentA;
import org.example.demo.StudentB;
import org.example.demo.UserDo;
import org.example.demo.UserDto;
import org.hello.dozer.spring.boot.autoconfigure.HelloDozerBeanMapper;
import org.javamoney.moneta.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: huanggq
 * @create: 2021-02-04 19:17
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DozerDemoApplication.class)
public class DozerDemoApplicationTest {


    @Autowired
    HelloDozerBeanMapper mapper;

    @Test
    public void testUserDto2UserDo() {
        UserDto dto = new UserDto("zhangsan", new BigDecimal(89.9), 1, "1999-12-23");
        UserDo userDo = mapper.map(dto, UserDo.class);
        System.out.println(userDo);
    }

    @Test
    public void test2() {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(2);

        Runnable run1 = new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("run1" + Thread.currentThread().getName() + "正在等待");
                    cdOrder.await();
                    System.out.println("run1" + Thread.currentThread().getName() + "已接受");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = sdf.parse("1999-01-01");
                        Calendar c = Calendar.getInstance();
                        c.setTime(date);
                        c.add(Calendar.DAY_OF_MONTH, 1);
                        date = c.getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    UserDo userDo = new UserDo("zhangsan" + 1, Money.of(1, "CNY"), false, date);
                    UserDto dto = mapper.map(userDo, UserDto.class);
                    System.out.println(dto);
                   /* UserDto dto = new UserDto("zhangsan" + 1, new BigDecimal(1), 1, "1999-01-01");
                    UserDo userDo = mapper.map(dto, UserDo.class);
                    System.out.println(userDo);*/

                    System.out.println("run1" + Thread.currentThread().getName() + "到达");
                    cdAnswer.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        service.execute(run1);

        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("run2" + Thread.currentThread().getName() + "正在等待");
                    cdOrder.await();
                    System.out.println("run2" + Thread.currentThread().getName() + "已接受");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    Date date = null;
                    try {
                        date = sdf.parse("20000101");
                        Calendar c = Calendar.getInstance();
                        c.setTime(date);
                        c.add(Calendar.DAY_OF_MONTH, 2);
                        date = c.getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    StudentA dto = new StudentA("student" + 2, new BigDecimal(2), 2, date);
                    StudentB studentB = mapper.map(dto, StudentB.class);
                    System.out.println(studentB);

                    System.out.println("run2" + Thread.currentThread().getName() + "到达");
                    cdAnswer.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        service.execute(run2);

        try {
            Thread.sleep((long) (Math.random() * 100));
            System.out.println("裁判" + Thread.currentThread().getName() + "即将发布口令");
            cdOrder.countDown();
            System.out.println("裁判" + Thread.currentThread().getName() + "已发送口令，正在等待所有选手到达终点");
            cdAnswer.await();
            System.out.println("所有选手都到达终点");
            System.out.println("裁判" + Thread.currentThread().getName() + "汇总成绩排名");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }


    @Test
    public void test3() {
        ExecutorService service = Executors.newCachedThreadPool();

        int threadCount = 100000;

        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(threadCount);


        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        //System.out.println("run" + finalI + Thread.currentThread().getName() + "正在等待");
                        cdOrder.await();
                        //System.out.println("run" + Thread.currentThread().getName() + "已接受");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        Date date = null;
                        try {
                            date = sdf.parse("20000101");
                            Calendar c = Calendar.getInstance();
                            c.setTime(date);
                            c.add(Calendar.DAY_OF_MONTH, finalI);
                            date = c.getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String name = "student" + finalI;
                        BigDecimal salary = new BigDecimal(finalI);
                        StudentA a = new StudentA(name, salary, finalI, date);
                        StudentB studentB = mapper.map(a, StudentB.class);
                        //System.out.println(studentB);
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                        if (!name.equals(studentB.getName())
                                || salary.compareTo(studentB.getSalary().getNumber().numberValue(BigDecimal.class)) != 0
                                || !studentB.getBirthday().equals(sdf2.format(date))) {
                            System.err.println(studentB);
                        }

                        //System.out.println("run" + finalI + Thread.currentThread().getName() + "到达");
                        cdAnswer.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }

        try {

            System.out.println("裁判" + Thread.currentThread().getName() + "即将发布口令");
            cdOrder.countDown();
            System.out.println("裁判" + Thread.currentThread().getName() + "已发送口令，正在等待所有选手到达终点");
            cdAnswer.await();
            System.out.println("所有选手都到达终点");
            System.out.println("裁判" + Thread.currentThread().getName() + "汇总成绩排名");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }


}