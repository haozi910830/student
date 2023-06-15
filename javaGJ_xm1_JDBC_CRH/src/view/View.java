package view;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.User;
import utile.JDBCUTILE;

public class View {

	private	Scanner scanner = new Scanner(System.in);

	private int b = 0;
	//初始化界面方法
	public void mainView() {

		while(true) {
			System.out.println("欢迎进入学生管理系统");
			System.out.println("1.登录");
			System.out.println("2.注册");
			System.out.println("3.退出");
			System.out.print("请输入您需要的序号：");

			String num = scanner.next();

			if("1".equals(num)) {
				userLogin();
			}else if ("2".equals(num)) {
				useRegister();
			}else if ("3".equals(num)) {
				System.err.println("欢迎下次光临！");
				break;
			}else {
				System.out.println("您的输入有误！请重新输入！\n");
			}
		}
	}
	//登录方法
	private void userLogin() {
		//调用获取数据库所有用户的方法
		List<User> userList = getAllUser();
		if(userList.size() > 0) {
			System.out.println("---------");
			System.out.println("|登陆界面|");
			System.out.println("----------");
			System.out.print("请输入登录的账号：");
			String userName = scanner.next();
			System.out.print("请输入登录的密码：");
			String userPass = scanner.next();
			int i = 0 ;
			for(User u:userList) {
				if(u.getUserName().equals(userName)) {
					if(u.getUserPass().equals(userPass)) {
						i = 1;
						System.out.println("恭喜您登录成功！学生管理系统的主界面正在加载中。。。。。。。。");
						break;
					}
				}
			}

			if(i==1) {
				//刷新登录次数
				b = 0;
				//调用学生管理系统的方法
				StudentView studentView = new StudentView();
				studentView.studentView(scanner);

			}else {
				System.err.println("登录失败！帐号或密码错误！\n");
				b++;
				if(b == 2) {
					System.out.println("您的帐号和密码已经错误两次了，再输入错误系统将会自动退出\n");
				}else if (b == 3) {
					System.err.println("帐号密码错误三次！系统自动退出");
					System.exit(0);
				}
			}
		}else {
			System.err.println("账号未注册，请先注册帐号后再登录\n");
		}

	}
	private List<User> getAllUser(){
		String SQL1 = "SELECT userName,userPass FROM MyUser";
		ResultSet rst = null;
		//接口指向实现类 储存数据库中所有帐号内容
		List<User> userList = new ArrayList<User>();

		try {
			rst = JDBCUTILE.selectDataBaseTable(SQL1);
			while(rst.next()){
				User user = new User();
				user.setUserName(rst.getString("userName"));
				user.setUserPass(rst.getString("userPass"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUTILE.dataBaseClose(rst, null, null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return userList;

	}

	//注册方法
	private void useRegister() {

		System.out.println("---------");
		System.out.println("|注册界面|");
		System.out.println("---------");
		System.out.print("请输入注册的账号：");
		String userName = scanner.next();
		System.out.print("请输入注册的密码：");
		String userPass = scanner.next();
		//截取账号首字母 userName.substring(0,1) 将String类型的字母 转化成 char 类型.charAt(0)
		char userNameIndex = userName.substring(0,1).charAt(0);//[0,1)
		//判断首字母大写 Character.isUpperCase(char类型)
		//如果Character.isUpperCase(userNameIndex)返回的结果是 T 代表判断的char类型的值 大写 如果是 F相反
		if(Character.isUpperCase(userNameIndex)) {
			//判断密码的长度是否大于6位
			if(userPass.length() > 6) {
				int i = 0;
				List<User> userList = getAllUser();
				if(userList.size() > 0) {
					for(User u:userList) {
						if(u.getUserName().equals(userName)) {
							i = 1;
							System.err.println("此帐号已被注册！请重新注册！\n");
							break;
						}
					}
				}
				if(i==0) {
				boolean bo = insertUser(userName, userPass);
					if(bo) {
						System.out.println("恭喜您注册成功！\n");
					}else {
						System.out.println("系统错误，注册失败！请稍后注册！\n");
					}
					userList.add(new User(userName,userPass));
				  }
			}else {
				System.err.println("密码长度必须大于6位!");
				System.out.println("------------------");
			}

		 }else {
			 	System.err.println("账号首字母必须大写！");
				System.out.println("------------------");
		 }
   }
	//用户信息注册到数据库的方法
	private boolean insertUser(String userName,String userPass) {
		String SQL2 = "INSERT INTO MyUser(userName,userPass) VALUES(?,?) ";
		boolean bo = false;
		try {
			bo = JDBCUTILE.upDataBaseTable(SQL2, userName,userPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo;
	}
}