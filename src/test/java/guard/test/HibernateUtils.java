package guard.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtils {
private static final SessionFactory sessionFactory;
	
	static {

        try {

            // Create the SessionFactory from hibernate.cfg.xml(默认不写必须要在classpath下面，且必须为这个文件名)
            //下面那种为注解配置实体的方式
            sessionFactory = new Configuration().configure().buildSessionFactory();
        	//sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {

            // Make sure you log the exception, as it might be swallowed

            System.err.println("Initial SessionFactory creation failed." + ex);

            throw new ExceptionInInitializerError(ex);

        }

    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;

    }
}
