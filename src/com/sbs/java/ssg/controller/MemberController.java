package com.sbs.java.ssg.controller;

import java.util.List;
import java.util.Scanner;

import com.sbs.java.ssg.dto.Member;
import com.sbs.java.ssg.util.Util;

public class MemberController {
	private Scanner sc;
	private List<Member> members;
	
	public MemberController(Scanner sc, List<Member> members) {
		this.sc = sc;
		this.members = members;
	}
	
	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		
		for ( Member member : members ) {
			if ( member.loginId.equals(loginId) ) {
				return i;
			}
			
			i++;
		}
		
		return -1;
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if ( index == -1 ) {
			return true;
		}
		
		return false;
	}

	public void doJoin() {
		int id = members.size() + 1;
		String regDate = Util.getNowDateStr();
		
		String loginId = null;
		
		while ( true ) {
			System.out.printf("�α��� ���̵� : ");
			loginId = sc.nextLine();
			
			if ( isJoinableLoginId(loginId) == false ) {
				System.out.printf("%s(��)�� �̹� ������� ���̵��Դϴ�.\n", loginId);
				continue;
			}
			
			break;
		}
		
		String loginPw = null;
		String loginPwConfirm = null;
		
		while ( true ) {
			System.out.printf("�α��� ��� : ");
			loginPw = sc.nextLine();
			System.out.printf("�α��� ���Ȯ�� : ");
			loginPwConfirm = sc.nextLine();
			
			if ( loginPw.equals(loginPwConfirm) == false ) {
				System.out.println("��й�ȣ�� �ٽ� �Է����ּ���.");
				continue;
			}
			
			break;
		}
		
		System.out.printf("�̸� : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d�� ȸ���� �����Ǿ����ϴ�. ȯ���մϴ�^^\n", id);
	}

}