package com.lanou3g.domain;

import com.lanou3g.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * .                       .::::.
 * .                     .::::::::.
 * .                    :::::::::::  Jack Slow fuck
 * .                ..:::::::::::'
 * .            '::::::::::::::'
 * .               ':::::::::::
 * .           '::::::::::::::..
 * .                ..::::::::::::.
 * .              ``::::::::::::::::
 * .               ::::``:::::::::'        .:::.
 * .              ::::'   ':::::'       .::::::::.
 * .            .::::'      ::::     .:::::::'::::.
 * .           .:::'       :::::  .:::::::::' ':::::.
 * .          .::'        :::::.:::::::::'      ':::::.
 * .         .::'         ::::::::::::::'         ``::::.
 * .     ...:::           ::::::::::::'              ``::.
 * .    ```` ':           ':::::::::'                  ::::..
 * .                       '.:::::'                    ':'````..
 */

public class StudentTest {
    private Session session;
    //单项多对多
    //可以查出来某个大学生学习了哪些课程,但是不能查出来某个课程由哪些学生学习了

    @Before
    public void init(){
        session = HibernateUtil.getSession().openSession();
        session.beginTransaction();
    }

    @Test
    public void save(){
        Student s1 = new Student("张威");
        Student s2 = new Student("倾城");

        Course c1 = new Course("语文");
        Course c2 = new Course("数学");
        Course c3 = new Course("英语");
        Course c4 = new Course("体育");
        s1.getCourses().add(c1);
        s1.getCourses().add(c2);

        s2.getCourses().add(c1);
        s2.getCourses().add(c2);
        s2.getCourses().add(c3);
        s2.getCourses().add(c4);

        session.save(s1);
        session.save(s2);
    }

    @Test
    public void query(){
        Student student = session.get(Student.class,1);
        System.out.println(student);
        System.out.println(student.getCourses().hashCode());

        //再次向数据库取出数据
        //观察log,如果只查询一次数据库
        //说明第二次取出来的数据不是从数据库查询出来的
        //用来验证Hibernate的一级缓存
        session.getTransaction().commit();
        session.close();

        session = HibernateUtil.getSession().openSession();
        session.beginTransaction();

        //一级缓存的生命周期是随着session变化的
        //如果session关闭,那么随之对应的缓存也会清空
        //如果再次打开session 那么会创建出新的缓存
        Student student1 = session.get(Student.class,1);
        System.out.println(student1);
        System.out.println(student1.getCourses().hashCode());
    }

    @After

    public void After(){
        session.getTransaction().commit();
        session.close();
    }

}