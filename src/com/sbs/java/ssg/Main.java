package com.sbs.java.ssg;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== ���α׷� ���� ==");

		Scanner sc = new Scanner(System.in);
		System.out.printf("��ɾ�) ");
		String command;
		command = sc.nextLine(); 
		System.out.printf("�Էµ� ��ɾ� : %s\n", command);


		sc.close();	
		System.out.println("== ���α׷� �� ==");
	}
}
