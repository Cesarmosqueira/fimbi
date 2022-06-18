package online.fimbi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.fimbi.Common.EntityDtoConverter;
import online.fimbi.Dto.BondDto;
import online.fimbi.Dto.IssuerDto;
import online.fimbi.Entities.Bond;
import online.fimbi.Entities.Issuer;
import online.fimbi.Exception.FimbiException;
import online.fimbi.Repositories.BondRepository;
import online.fimbi.Repositories.IssuerRepository;

@Transactional
@Service
public class IssuerService {
	@Autowired
	private IssuerRepository issuerRepository;

	@Autowired
	private BondRepository bondRepository;
	@Autowired
	EntityDtoConverter entityDtoConverter;

	public Issuer save_issuer(IssuerDto issuerDto) throws FimbiException {
		if (issuerRepository.count_issuers(issuerDto.getMarket_identifier()) == 0) {
			Issuer issuer = new Issuer(issuerDto);
			return issuerRepository.save(issuer);
		} else {
			throw new FimbiException(issuerDto.getMarket_identifier() + " is already registered");
		}
	}

	@Transactional
	public IssuerDto getByIdentifier(String identifier) throws FimbiException {
		Issuer issuer = issuerRepository.getByIdentifier(identifier)
				.orElseThrow(() -> new FimbiException(identifier + " not found"));
		return entityDtoConverter.convertIssuerToDto(issuer);
	}

	@Transactional
	public List<IssuerDto> getAll() {
		List<Issuer> issuers = issuerRepository.findAll();
		return entityDtoConverter.convertIssuersToDto(issuers);
	}

	public List<BondDto> list_bonds(String identifier) {
		List<Bond> bonds = bondRepository.list_bonds_by_issuer(identifier);
		return entityDtoConverter.convertBondsToDto(bonds);
	}

}
