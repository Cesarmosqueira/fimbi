package online.fimbi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.fimbi.Dto.IssuerDto;
import online.fimbi.Services.IssuerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/issuers")
public class IssuerController {
	@Autowired
	IssuerService issuerService;

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
}
