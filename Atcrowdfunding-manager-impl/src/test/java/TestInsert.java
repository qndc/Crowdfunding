import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.MD5Util;

public class TestInsert {

	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:spring/spring*.xml");
		
		UserMapper userMapper = ioc.getBean(UserMapper.class);
		
		for (int i = 1; i < 50; i++) {
			User user = new User();
			user.setLoginacct("test"+i);
			user.setUserpswd(MD5Util.getMD5("test"+i));
			user.setUsername("test"+i);
			user.setCreatetime("2019-09-25 16:02:24");
			user.setEmail("dc@drper.cn");
			userMapper.insert(user);
		}
	}
	
}
