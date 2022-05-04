package com.chocoapp.chocoappapi.converter;

import java.util.ArrayList;
import java.util.List;

import com.chocoapp.chocoappapi.dto.ChocolateDTO;
import com.chocoapp.chocoappapi.model.Chocolates;

public class ChocoConverter {

	public static ChocolateDTO toDTO(Chocolates c) {
		ChocolateDTO candy = new ChocolateDTO();
		candy.setId(c.getId());
		candy.setName(c.getName());
		candy.setPrice(c.getPrice());
		candy.setQuantity(c.getQuantity());
		return candy;
	}

	public static List<ChocolateDTO> toDTO(List<Chocolates> chocos) {
		List<ChocolateDTO> list = new ArrayList<>();
		for (Chocolates c : chocos) {
			ChocolateDTO candy = toDTO(c);
			list.add(candy);
		}
		return list;
	}

	public static Chocolates toModel(ChocolateDTO c) {
		Chocolates candy = new Chocolates();
		candy.setId(c.getId());
		candy.setName(c.getName());
		candy.setPrice(c.getPrice());
		candy.setQuantity(c.getQuantity());
		return candy;
	}

	public static List<Chocolates> toModel(List<ChocolateDTO> chocolateDTOList) {
		List<Chocolates> chocos = new ArrayList<>();
		for (ChocolateDTO c : chocolateDTOList) {
			Chocolates candy = toModel(c);
			chocos.add(candy);
		}
		return chocos;
	}
}
