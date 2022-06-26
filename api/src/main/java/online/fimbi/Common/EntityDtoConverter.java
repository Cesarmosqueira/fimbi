package online.fimbi.Common;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import online.fimbi.Dto.BondDto;
import online.fimbi.Dto.BondDtoRes;
import online.fimbi.Dto.IssuerDto;
import online.fimbi.Dto.IssuerDtoRes;
import online.fimbi.Dto.UserDto;
import online.fimbi.Entities.Bond;
import online.fimbi.Entities.Issuer;
import online.fimbi.Entities.User;

@Component
public class EntityDtoConverter {
	private final ModelMapper modelMapper;

	public EntityDtoConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public BondDto convertBondToDto(Bond bond) {
		return modelMapper.map(bond, BondDto.class);
	}

	public BondDtoRes convertBondToDtoBig(Bond bond) {
		return modelMapper.map(bond, BondDtoRes.class);
	}

	public List<BondDto> convertBondsToDto(List<Bond> bonds) {
		return bonds.stream().map(this::convertBondToDto).collect(Collectors.toList());
	}

	public UserDto convertUserToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	public List<UserDto> convertUsersToDto(List<User> users) {
		return users.stream().map(this::convertUserToDto).collect(Collectors.toList());
	}

	public IssuerDto convertIssuerToDto(Issuer issuer) {
		return modelMapper.map(issuer, IssuerDto.class);
	}

	public IssuerDtoRes convertIssuerToDtoRes(Issuer issuer) {
		return modelMapper.map(issuer, IssuerDtoRes.class);
	}

	public List<IssuerDto> convertIssuersToDto(List<Issuer> issuers) {
		return issuers.stream().map(this::convertIssuerToDto).collect(Collectors.toList());
	}

}
