package com.sbs.java.ssg.controller;

import java.util.List;
import java.util.Scanner;

import com.sbs.java.ssg.container.Container;
import com.sbs.java.ssg.dto.Member;
import com.sbs.java.ssg.util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private List<Member> members;
	private String command;
	private String actionMethodName;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		members = Container.memberDao.members;
	}
	
	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		
		switch ( actionMethodName ) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("�������� �ʴ� ��ɾ� �Դϴ�.");
			break;
		}
	}

	public void makeTestData() {
		System.out.println("�׽�Ʈ�� ���� �����͸� �����մϴ�.");
		
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "admin", "admin", "������"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "user1", "user1", "ȫ�浿"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "user2", "user2", "ȫ���"));
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
	
	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if ( index == -1 ) {
			return null;
		}
		
		return members.get(index);
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if ( index == -1 ) {
			return true;
		}
		
		return false;
	}

	public void doJoin() {
		int id = Container.memberDao.getNewId();
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
		Container.memberDao.add(member);

		System.out.printf("%d�� ȸ���� �����Ǿ����ϴ�. ȯ���մϴ�^^\n", id);
	}
	
	private void doLogin() {		
		System.out.printf("�α��� ���̵� : ");
		String loginId = sc.nextLine();
		System.out.printf("�α��� ��� : ");
		String loginPw = sc.nextLine();
		
		// �Է¹��� ���̵� �ش��ϴ� ȸ���� �����ϴ���
		Member member = getMemberByLoginId(loginId);
		
		if ( member == null ) {
			System.out.println("�ش� ȸ���� �������� �ʽ��ϴ�.");
			return;
		}
		
		if ( member.loginPw.equals(loginPw) == false ) {
			System.out.println("��й�ȣ�� ���� �ʽ��ϴ�.");
			return;
		}
		
		loginedMember = member;
		
		System.out.printf("�α��� ����! %s�� ȯ���մϴ�!\n", loginedMember.name);
	}
	private void doLogout() {
		loginedMember = null;
		
		System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
	}

}