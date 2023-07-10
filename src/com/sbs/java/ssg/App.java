package com.sbs.java.ssg;

import java.util.Scanner;

import com.sbs.java.ssg.controller.ArticleController;
import com.sbs.java.ssg.controller.Controller;
import com.sbs.java.ssg.controller.MemberController;

public class App {
	public void start() {
		System.out.println("== ���α׷� ���� ==");

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData();
		memberController.makeTestData();

		while (true) {
			System.out.printf("��ɾ�) ");
			String command = sc.nextLine();
			command = command.trim();

			if (command.length() == 0) {
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}
			
			String[] commandBits = command.split(" "); // article detail
			
			if ( commandBits.length == 1 ) {
				System.out.println("�������� �ʴ� ��ɾ� �Դϴ�.");
				continue;
			}
			
			String controllerName = commandBits[0]; // article 
			String actionMethodName = commandBits[1]; // detail 
			
			Controller controller = null;
			
			if ( controllerName.equals("article") ) {
				controller = articleController;
			} else if ( controllerName.equals("member") ) {
				controller = memberController;
			}
			
			controller.doAction(command, actionMethodName);
		}

		sc.close();
		System.out.println("== ���α׷� �� ==");
	}
}