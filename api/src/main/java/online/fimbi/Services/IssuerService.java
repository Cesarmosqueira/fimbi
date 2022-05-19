package online.fimbi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.fimbi.Dto.IssuerDto;
import online.fimbi.Entities.Issuer;
import online.fimbi.Repositories.IssuerRepository;

@Transactional
@Service
public class IssuerService {
	@Autowired
	private IssuerRepository issuerRepository;

	public Issuer save_issuer(IssuerDto issuerDto) {
		Issuer issuer = new Issuer(issuerDto);
		return issuerRepository.save(issuer);
	}
}
