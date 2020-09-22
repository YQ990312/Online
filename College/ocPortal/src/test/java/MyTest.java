import com.online.college.dao.auth.AuthUserDao;
import com.online.college.service.auth.pojo.AuthUser;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void showCarousels() {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        AuthUserDao authUserDao=(AuthUserDao)context.getBean("authUserDao");
        AuthUser authUser=authUserDao.queryByUserName("15179038625");
        authUser.setUsername("15179031111");
        authUserDao.updateUserInfo(
                authUser
        );

    }
}
