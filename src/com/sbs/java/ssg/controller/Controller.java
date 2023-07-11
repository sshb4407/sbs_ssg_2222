package com.sbs.java.ssg.controller;

import com.sbs.java.ssg.dto.Member;

public abstract class Controller {
	public static Member loginedMember;
	
	public static boolean isLogined() {
		return loginedMember != null;
	}
	
	public abstract void doAction(String command, String actionMethodName);
	
	public abstract void makeTestData();
}