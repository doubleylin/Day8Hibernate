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

public class CourseTest {

    //测试多对多双向关联

    private Session session;
    @Before
    public void init(){
        session = HibernateUtil.getSession().openSession();
        session.beginTransaction();
    }

    @Test
    public void save(){
        Student s1 = new Student("张威");
        Student s2 = new Student("东少");

        Course c1 = new Course("语文");
        Course c2 = new Course("数学");
        Course c3 = new Course("英语");
        Course c4 = new Course("体育");
//        s1.getCourses().add(c1);
//        s1.getCourses().add(c2);
//
//        s2.getCourses().add(c1);
//        s2.getCourses().add(c2);
//        s2.getCourses().add(c3);
//        s2.getCourses().add(c4);
        c1.getStudents().add(s1);
        c1.getStudents().add(s2);
        c2.getStudents().add(s2);
        c3.getStudents().add(s1);
        c3.getStudents().add(s2);
        c4.getStudents().add(s1);



        session.save(c1);
        session.save(c2);
        session.save(c3);
        session.save(c4);
    }

    @Test
    public void query(){
        Student s1 = session.get(Student.class,1);
        System.out.println(s1);
        System.out.println(s1.getCourses());

        Course course = session.get(Course.class,2);
        System.out.println(course);
        System.out.println(course.getStudents());




    }




    @After

    public void After(){
        session.getTransaction().commit();
        session.close();
    }

}