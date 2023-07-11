package com.sbs.java.ssg.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.ssg.dto.Article;
import com.sbs.java.ssg.dto.Member;
import com.sbs.java.ssg.util.Util;

public class ArticleController extends Controller {
	private Scanner sc;
	private List<Article> articles;
	private String command;
	private String actionMethodName;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articles = new ArrayList<Article>();
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "write":
			doWrite();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("�������� �ʴ� ��ɾ� �Դϴ�.");
			break;
		}
	}

	public void makeTestData() {
		System.out.println("�׽�Ʈ�� ���� �����͸� �����մϴ�.");

		articles.add(new Article(1, Util.getNowDateStr(), 1, "����1", "����1", 10));
		articles.add(new Article(2, Util.getNowDateStr(), 2, "����2", "����2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), 2, "����3", "����3", 33));
	}

	private int getArticleIndexById(int id) {
		int i = 0;

		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}

			i++;
		}

		return -1;
	}

	private Article getArticleById(int id) {
		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

	public void doWrite() {
		int id = articles.size() + 1;
		String regDate = Util.getNowDateStr();
		System.out.printf("���� : ");
		String title = sc.nextLine();
		System.out.printf("���� : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, loginedMember.id, title, body);
		articles.add(article);

		System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", id);
	}

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("�Խù��� �����ϴ�.");
			return;
		}

		String searchkeyword = command.substring("article list".length()).trim();

		List<Article> forListArticles = articles;

		if (searchkeyword.length() > 0) {
			forListArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchkeyword)) {
					forListArticles.add(article);
				}

				if (articles.size() == 0) {
					System.out.println("�˻������ �������� �ʽ��ϴ�.");
					continue;
				}
			}
		}

		System.out.println("��ȣ | �ۼ��� | ��ȸ | ����");
		for (int i = forListArticles.size() - 1; i >= 0; i--) {
			Article article = forListArticles.get(i);

			System.out.printf("%4d | %6d | %4d | %s\n", article.id, article.memberId, article.hit, article.title);
		}
	}

	public void showDetail() {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
			return;
		}

		foundArticle.increseHit();

		System.out.printf("��ȣ : %d\n", foundArticle.id);
		System.out.printf("��¥ : %s\n", foundArticle.regDate);
		System.out.printf("�ۼ��� : %s\n", foundArticle.memberId);
		System.out.printf("���� : %s\n", foundArticle.title);
		System.out.printf("���� : %s\n", foundArticle.body);
		System.out.printf("��ȸ : %s\n", foundArticle.hit);
	}

	public void doModify() {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("������ �����ϴ�.");
			return;
		}

		System.out.printf("���� : ");
		String title = sc.nextLine();
		System.out.printf("���� : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d�� �Խù��� �����Ǿ����ϴ�.\n", id);
	}

	public void doDelete() {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("������ �����ϴ�.");
			return;
		}

		articles.remove(foundArticle);
		System.out.printf("%d�� �Խù��� �����Ǿ����ϴ�.\n", id);
	}

}