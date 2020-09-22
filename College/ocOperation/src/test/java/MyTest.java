
import com.online.operation.service.course.ICourseService;
import com.online.operation.service.course.impl.CourseServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyTest {
    @Test
    public void myTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        ICourseService courseService=(CourseServiceImpl)context.getBean("courseServiceImpl");
       System.out.println(courseService.queryLast());
    }
}
