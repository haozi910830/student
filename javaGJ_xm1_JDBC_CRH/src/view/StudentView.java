package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.student;

public class StudentView {
	private List<student> stuList = new ArrayList<student>();

	public StudentView() {
		stuList.add(new student(1,"张三","男",20,"11301"));
		stuList.add(new student(2,"李四","男",21,"11302"));
		stuList.add(new student(3,"王五","男",20,"11301"));
		stuList.add(new student(4,"麻六","男",19,"11301"));
		stuList.add(new student(5,"赵七","男",18,"11301"));
	}

	public void studentView(Scanner scanner) {
		while(true) {
			//进入主界面
			System.out.println("\n欢迎使用青凌学生管理系统");
			System.out.println("-----------------------------");
			System.out.println("1、显示所有学生信息");
			System.out.println("2、根据编号查询学生信息");
			System.out.println("3、根据编号修改学生年龄");
			System.out.println("4、根据姓名修改学生班级");
			System.out.println("5、根据班级查询全部学生信息");
			System.out.println("6、退出");
			System.out.println("-----------------------------");
			System.out.print("请选择[1/2/3/4/5/6]：");
			String num = scanner.next();
			if("1".equals(num)) {
				//\t代表 tab 建，自动对齐
				//\n代表换行的意思
				selectAllStu(scanner);
			}else if ("2".equals(num)) {
				selecStuById(scanner);
			}else if("3".equals(num)) {
				reviseStuageById(scanner);
			}else if("4".equals(num)) {
				changeTheStuClassByName(scanner);
			}else if("5".equals(num)) {
				reviseAllStuageByClass(scanner);
			}else if("6".equals(num)) {
				System.err.println("退出当前学生信息界面，跳转回登录界面！");
					break;
			}else {
					System.err.println("序号输入有误！请重新输入！");
				}
			}
		}
	//1.显示全部学生方法
	public void selectAllStu(Scanner scanner) {
		System.out.println("编号\t\t姓名\t\t性别\t\t年龄\t\t班级");
		for(student s:stuList) {
			System.out.println(s.getStuId() + "\t\t"
							 + s.getStuName() + "\t\t"
							 + s.getStuSex() + "\t\t"
							 + s.getStuAge() + "\t\t"
							 + s.getStuClass());
			}
		System.out.println("[任意字符继续]:");
		String pd = scanner.next();
	}
	//按照学生ID查找学生方法
	public void selecStuById(Scanner scanner) {
		System.out.print("请输入学生编号：");
		String stuId = scanner.next();
		System.out.println("编号\t\t姓名\t\t性别\t\t年龄\t\t班级");
		int b = 0;
		if(stuList.size() > 0) {
			for(student s:stuList) {
				if((s.getStuId()+"").equals(stuId)) {
					b = 1;
					System.out.println(s.getStuId() + "\t\t"
							 + s.getStuName() + "\t\t"
							 + s.getStuSex() + "\t\t"
							 + s.getStuAge() + "\t\t"
							 + s.getStuClass());
					break;
				}
			}
		}

		if(b == 0) {
			System.out.println("您输入的学生编号有误或此学生信息不存在！");
		}else {
			System.err.println("您输入的学生ID不存在");
		}
		System.out.print("[任意字符继续]:");
		String pd = scanner.next();
	}
	//根据学生ID修改年龄方法
	public void reviseStuageById(Scanner scanner) {
		System.out.print("请输入需要修改的学生编号：");
		String stuId = scanner.next();
		//判断是否找到了学生
		int c = 0;
		if(stuList.size() > 0) {
		for(student s:stuList) {
			if((s.getStuId() + "").equals(stuId)) {
				c = 1;
				System.out.println("编号\t\t姓名\t\t性别\t\t年龄\t\t班级");
				System.out.println(s.getStuId() + "\t\t"
						 + s.getStuName() + "\t\t"
						 + s.getStuSex() + "\t\t"
						 + s.getStuAge() + "\t\t"
						 + s.getStuClass());
				System.out.print("请输入修改后的年龄：");
				int stuAge = scanner.nextInt();
				if(stuAge >= 18 && stuAge <= 30) {
					s.setStuAge(stuAge);
					System.out.println("学生年龄修改成功！");
				}else {
					System.err.println("年龄必须在18到30岁之间！");
				}
				break;
				}
			}
		}
		if(c == 0) {
			System.err.println("您输入的学生编号有误或此学生信息不存在！");
		}
		System.out.print("[任意字符继续]:");
		String pd = scanner.next();

	}
	//根据姓名修改学生班级
	public void changeTheStuClassByName(Scanner scanner) {
		System.out.print("请输入需要修改的学生姓名：");
		String stuName = scanner.next();
		//判断学生是否存在
		int d = 0;
		if(stuList.size() > 0) {
			for(student s:stuList) {
				if(s.getStuName().equals(stuName)) {
					d = 1;
					System.out.println("编号\t\t姓名\t\t性别\t\t年龄\t\t班级");
					System.out.println(s.getStuId() + "\t\t"
							 + s.getStuName() + "\t\t"
							 + s.getStuSex() + "\t\t"
							 + s.getStuAge() + "\t\t"
							 + s.getStuClass());
					System.out.println("请输入修改后的学生班级：");
					String stuClass = scanner.next();
					s.setStuClass(stuClass);
					System.out.println("恭喜您修改成功！");
					break;
				}
			}
		}

		if(d == 0) {
			System.err.println("您输入的学生编号有误或此学生信息不存在！");
		}
		System.out.print("[任意字符继续]:");
		String pd = scanner.next();
	}
	//根据班级查询全部学生信息
	public void reviseAllStuageByClass(Scanner scanner){
		System.out.println("请输入需要查询的班级");
		String stuClass = scanner.next();
		//判断是否存在此班级
		int e = 0;
		if(stuList.size() > 0) {
			System.out.println("编号\t\t姓名\t\t性别\t\t年龄\t\t班级");
			for(student s:stuList) {
				if(s.getStuClass().equals(stuClass)) {
					e = 1;
					System.out.println(s.getStuId() + "\t\t"
							 + s.getStuName() + "\t\t"
							 + s.getStuSex() + "\t\t"
							 + s.getStuAge() + "\t\t"
							 + s.getStuClass());
				}
			}
		}

		if(e == 0) {
			System.err.println("您输入的学生班级有误或此班级不存在！");
		}
		System.out.print("[任意字符继续]:");
		String pd = scanner.next();
	}

}