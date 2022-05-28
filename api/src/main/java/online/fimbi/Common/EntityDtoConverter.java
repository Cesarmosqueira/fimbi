package online.fimbi.Common;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import online.fimbi.Dto.BondDto;
import online.fimbi.Entities.Bond;

@Component
public class EntityDtoConverter {
	private final ModelMapper modelMapper;

	public EntityDtoConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public BondDto convertBondToDto(Bond bond) {
		return modelMapper.map(bond, BondDto.class);
	}

	public List<BondDto> convertBondsToDto(List<Bond> bonds) {
		return bonds.stream().map(this::convertBondToDto).collect(Collectors.toList());
	}
}
