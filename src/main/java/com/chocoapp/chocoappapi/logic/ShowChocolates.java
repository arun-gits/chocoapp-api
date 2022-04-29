package com.chocoapp.chocoappapi.logic;

import java.util.ArrayList;
import java.util.List;

import com.chocoapp.chocoappapi.model.Chocolates;

public class ShowChocolates {

	List<Chocolates> menu = new ArrayList<Chocolates>();

	public static void list(List<Chocolates> menu) throws Exception {

		for (int i = 0; i < 48; i++) {
			System.out.printf("=");
		}
		System.out.printf("\n");
		System.out.printf("   ID");
		System.out.printf("%14s", "Name");
		System.out.printf("%24s", "Price");
		System.out.println();
		for (Chocolates c : menu) {
			System.out.print("    " + c.getId() + "    ");
			System.out.printf(c.getName());
			System.out.printf(" ");
			for (int i = 0; i < 10; i++) {
				System.out.printf("-");
			}
			System.out.printf("%6s", c.getPrice());
			System.out.printf("\n");
		}
		for (int i = 0; i < 48; i++) {
			System.out.printf("=");
		}
	}
}