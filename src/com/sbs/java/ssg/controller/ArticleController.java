package com.sbs.java.ssg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.ssg.dto.Article;
import com.sbs.java.ssg.util.Util;

public class ArticleController {
	private Scanner sc;
	private List<Article> articles;

	public ArticleController(Scanner sc, List<Article> articles) {
		this.sc = sc;
		this.articles = articles;
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

		Article article = new Article(id, regDate, title, body);
		articles.add(article);

		System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", id);
	}

	public void showList(String command) {
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

		System.out.println("��ȣ | ��ȸ | ����");
		for (int i = forListArticles.size() - 1; i >= 0; i--) {
			Article article = forListArticles.get(i);

			System.out.printf("%4d | %4d | %s\n", article.id, article.hit, article.title);
		}
	}

	public void showDetail(String command) {
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
		System.out.printf("���� : %s\n", foundArticle.title);
		System.out.printf("���� : %s\n", foundArticle.body);
		System.out.printf("��ȸ : %s\n", foundArticle.hit);
	}

	public void doModify(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
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

	public void doDelete(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);

		int foundIndex = getArticleIndexById(id);

		if (foundIndex == -1) {
			System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
			return;
		}

		articles.remove(foundIndex);
		System.out.printf("%d�� �Խù��� �����Ǿ����ϴ�.\n", id);
	}

}