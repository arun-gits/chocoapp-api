package com.chocoapp.chocoappapi.converter;

import java.util.ArrayList;
import java.util.List;

import com.chocoapp.chocoappapi.dto.ChocolateDTO;
import com.chocoapp.chocoappapi.model.Chocolate;

public class ChocoConverter {

	private ChocoConverter() {
		super();
	}

	public static ChocolateDTO toDTO(Chocolate chocolate) {
		ChocolateDTO chocolateDto = new ChocolateDTO();
		chocolateDto.setId(chocolate.getId());
		chocolateDto.setName(chocolate.getName());
		chocolateDto.setPrice(chocolate.getPrice());
		chocolateDto.setQuantity(chocolate.getQuantity());
		return chocolateDto;
	}

	public static List<ChocolateDTO> toDTO(List<Chocolate> chocolates) {
		List<ChocolateDTO> chocolatesDto = new ArrayList<>();
		for (Chocolate c : chocolates) {
			ChocolateDTO chocolateDto = toDTO(c);
			chocolatesDto.add(chocolateDto);
		}
		return chocolatesDto;
	}

	public static Chocolate toModel(ChocolateDTO chocolateDto) {
		Chocolate chocolate = new Chocolate();
		chocolate.setId(chocolateDto.getId());
		chocolate.setName(chocolateDto.getName());
		chocolate.setPrice(chocolateDto.getPrice());
		chocolate.setQuantity(chocolateDto.getQuantity());
		return chocolate;
	}

	public static List<Chocolate> toModel(List<ChocolateDTO> chocolatesDto) {
		List<Chocolate> chocolates = new ArrayList<>();
		for (ChocolateDTO chocolateDto : chocolatesDto) {
			Chocolate chocolate = toModel(chocolateDto);
			chocolates.add(chocolate);
		}
		return chocolates;
	}
}
