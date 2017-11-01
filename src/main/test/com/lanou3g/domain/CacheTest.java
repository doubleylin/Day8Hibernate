package com.lanou3g.domain;

import com.lanou3g.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;

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

public class CacheTest {

    @Test
    public void test(){
        //验证二级缓存

        SessionFactory sf = HibernateUtil.getSession();
        Session session = sf.openSession();
        session.beginTransaction();
        //第一次取数据
        Student student = session.get(Student.class,1);
        System.out.println(student);

        session.getTransaction().commit();
        session.close();

        //第二次取数据
        Session session1 = sf.openSession();
        session1.beginTransaction();
        Student stu = session1.get(Student.class,1);
        System.out.println(stu);
        session1.getTransaction().commit();
        session1.close();

    }

    @Test
    public void current(){
        SessionFactory sf = HibernateUtil.getSession();
        //如果之前开启过session,那么直接会获取到之前的session对象
        //如果没有开启过,会打开一个新的session对象
        //这样获取的session,会在事务提交之后自动关闭
        Session cs = sf.getCurrentSession();
        cs.beginTransaction();
        Student stu = cs.get(Student.class,1);
        System.out.println(stu);
        cs.getTransaction().commit();
        //下面的代码会出错,因为session随着事物的提交自动关闭了
        cs.beginTransaction();
        Course course = cs.get(Course.class,1);
        System.out.println(course);
        cs.getTransaction().commit();
    }


    @Test
    public void query(){
        Session session = HibernateUtil.getSession().openSession();
        session.beginTransaction();
        Query<Course> query = session.createQuery("from Course where id > ?",Course.class);
        Query<Course> query1 = session.createQuery("from Course where id > :value",Course.class);
        //第一个参数代表第几个问号
        //第二个参数代表问号的值
        query.setParameter(0,2);
        query1.setParameter("value",3);

        query.list().forEach(System.out::println);
        query1.list().forEach(System.out::println);
        session.getTransaction().commit();
    }
}
