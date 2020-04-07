import com.lagou.edu.SpringConfig;
import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.utils.MyReflect;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author 应癫
 */
public class IoCTest {


    @Test
    public void testIoC() throws Exception {

        // 通过读取classpath下的xml文件来启动容器（xml模式SE应用下推荐）
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");

        System.out.println(accountDao);



    }

    @Test
    public void testMyAutoWired() throws Exception {
        AccountDao bMybean=new AccountDao() {
            @Override
            public Account queryAccountByCardNo(String cardNo) throws Exception {
                return new Account();
            }

            @Override
            public int updateAccountByCardNo(Account account) throws Exception {
                return 0;
            }
        };
        //手动注入
        boolean b= MyReflect.setObjByFieldAnno(bMybean);
        if (b){
            Account account = bMybean.queryAccountByCardNo("6029621011001");
        }else{
            System.out.println("getObjByFieldAnno   不正确");
        }


    }
}
