package online.fimbi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import online.fimbi.Common.EntityDtoConverter;
import online.fimbi.Dto.BondDto;
import online.fimbi.Dto.IssuerDto;
import online.fimbi.Services.IssuerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/issuers")
public class IssuerController {
	@Autowired
	IssuerService issuerService;
	@Autowired
	EntityDtoConverter entityDtoConverter;

	@PostMapping
	public ResponseEntity<IssuerDto> post_issuer(@RequestBody IssuerDto issuerDto) {
		issuerService.save_issuer(issuerDto);
		return new ResponseEntity<>(issuerDto, HttpStatus.OK);
	}

	@PostMapping("random")
	public ResponseEntity<IssuerDto> post_random_issuer() {
		IssuerDto issuerDto = IssuerDto.random_issuer();
		issuerService.save_issuer(issuerDto);
		return new ResponseEntity<>(issuerDto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<IssuerDto>> getIssuer() {
		List<IssuerDto> issuers = issuerService.getAll();
		return new ResponseEntity<>(issuers, HttpStatus.OK);
	}

	@GetMapping("{identifier}")
	public ResponseEntity<IssuerDto> getIssuer(@PathVariable String identifier) {
		IssuerDto issuerDto = issuerService.getByIdentifier(identifier);
		return new ResponseEntity<>(issuerDto, HttpStatus.OK);
	}

	@GetMapping("list_bonds")
	public ResponseEntity<List<BondDto>> list_bonds(@RequestParam String identifier) {
		List<BondDto> bonds = issuerService.list_bonds(identifier);
		return new ResponseEntity<>(bonds, HttpStatus.OK);
	}

}
