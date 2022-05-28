package online.fimbi.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.fimbi.Common.EntityDtoConverter;
import online.fimbi.Dto.BondDto;
import online.fimbi.Entities.Bond;
import online.fimbi.Exception.FimbiException;
import online.fimbi.Repositories.BondRepository;
import online.fimbi.Repositories.IssuerRepository;

@Transactional
@Service
public class BondService {
	@Autowired
	private IssuerRepository issuerRepository;

	@Autowired
	private BondRepository bondRepository;

	@Autowired
	private EntityDtoConverter entityDtoConverter;

	public Bond save_bond(BondDto bondDto) throws FimbiException {
		String issuer_identifier = bondDto.getIssuer_identifier();
		List<Long> ids_for_identifier = issuerRepository.identifier_exists(issuer_identifier);

		if (ids_for_identifier.isEmpty()) {
			throw new FimbiException(String.format("Unknown identifier: '%s'", issuer_identifier));
		}
		Bond bond = new Bond(bondDto, ids_for_identifier.get(0));

		if (ids_for_identifier.size() > 1) {
			System.out.println(String.format("[WARNING] There are more than one issuers by the name of %s",
					issuer_identifier));
		}
		return bondRepository.save(bond);
	}

	public List<BondDto> get_lastest_bonds(int size) throws FimbiException {
		List<Bond> last_bonds = bondRepository.latest_bonds(size);
		return entityDtoConverter.convertBondsToDto(last_bonds);
	}
}
