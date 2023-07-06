package com.sbs.java.ssg;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== ���α׷� ���� ==");
		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		
		while ( true ) {			
			System.out.printf("��ɾ�) ");
			String command = sc.nextLine();
			command = command.trim();
			
			if ( command.length() == 0 ) {
				continue;
			}
			
			if ( command.equals("system exit") ) {
				break;
			}
			else if ( command.equals("article write") ) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				
				System.out.printf("%d�� ���� �ۼ��Ǿ����ϴ�.\n", id);
			}
			else if ( command.equals("article list") ) {
				System.out.println("�Խù��� �����ϴ�.");
			}
			else {
				System.out.printf("%s(��)�� �������� �ʴ� ��ɾ� �Դϴ�.\n", command);
			}
		}
		
		sc.close();	
		System.out.println("== ���α׷� �� ==");
	}
}