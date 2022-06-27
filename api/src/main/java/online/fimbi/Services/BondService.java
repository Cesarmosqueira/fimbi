package online.fimbi.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.fimbi.Common.EntityDtoConverter;
import online.fimbi.Dto.BondDto;
import online.fimbi.Dto.BondDtoRes;
import online.fimbi.Dto.DotDto;
import online.fimbi.Dto.GraphDto;
import online.fimbi.Dto.PurchaseDto;
import online.fimbi.Entities.Bond;
import online.fimbi.Entities.Issuer;
import online.fimbi.Entities.User;
import online.fimbi.Entities.UserxBond;
import online.fimbi.Exception.FimbiException;
import online.fimbi.Repositories.BondRepository;
import online.fimbi.Repositories.IssuerRepository;
import online.fimbi.Repositories.UserRepository;
import online.fimbi.Repositories.UserxbondRepository;

@Transactional
@Service
public class BondService {
	@Autowired
	private IssuerRepository issuerRepository;

	@Autowired
	private BondRepository bondRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserxbondRepository userxbondRepository;

	@Autowired
	private EntityDtoConverter entityDtoConverter;

	public Bond save_bond(BondDto bondDto) throws FimbiException {
		String issuer_identifier = bondDto.getIssuer_identifier();
		List<Long> ids_for_identifier = issuerRepository.identifier_exists(issuer_identifier);

		if (ids_for_identifier.isEmpty()) {
			throw new FimbiException(String.format("Unknown identifier: '%s'", issuer_identifier));
		}
		Bond bond = new Bond(bondDto, ids_for_identifier.get(0));
		if (bond.getError() == 1) {
			throw new FimbiException("That date won't work");
		}

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

	public List<BondDto> getBonds() {
		List<Bond> bonds = bondRepository.get_all_by_date();
		return entityDtoConverter.convertBondsToDto(bonds);
	}

	public BondDtoRes getById(Long bond_id) throws FimbiException {
		Bond bond = bondRepository.findById(bond_id)
				.orElseThrow(() -> new FimbiException("bond #" + bond_id + " not found"));
		return entityDtoConverter.convertBondToDtoBig(bond);
	}

	public GraphDto getByCashId(Long bond_id) throws FimbiException {
		Bond bond = bondRepository.findById(bond_id)
				.orElseThrow(() -> new FimbiException("bond #" + bond_id + " not found"));
		return bond.getCashFlow();
	}

	public List<PurchaseDto> get_lastest_purchases(int size) {
		List<UserxBond> rels = userxbondRepository.get_lastest_purchases(size);
		List<PurchaseDto> out = new ArrayList<>();
		for (UserxBond rel : rels) {
			Long user_id = rel.getUser_id();
			Long bond_id = rel.getBond_id();
			User u = userRepository.getById(user_id);
			Bond b = bondRepository.getById(bond_id);
			Date pd = new Date();
			out.add(new PurchaseDto(
					entityDtoConverter.convertUserToDto(u),
					entityDtoConverter.convertBondToDto(b),
					pd));
		}
		return out;

	}

	public List<BondDto> getBondsByUsername(String username) {
		User user = userRepository.getByUsername(username)
				.orElseThrow(() -> new FimbiException("User '" + username + "' not found"));
		List<Bond> bonds = bondRepository.bondsByUserId(user.getId());
		return entityDtoConverter.convertBondsToDto(bonds);
	}

	public List<BondDto> getBondsByIdentifier(String identifier) {
		Issuer issuer = issuerRepository.getByIdentifier(identifier)
				.orElseThrow(() -> new FimbiException("Issuer '" + identifier + "' not found"));
		List<Bond> bonds = bondRepository.bondsByIssuerId(issuer.getId());
		return entityDtoConverter.convertBondsToDto(bonds);
	}
}
