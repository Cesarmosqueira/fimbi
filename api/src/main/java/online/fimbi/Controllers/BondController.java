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
import org.springframework.web.bind.annotation.RestController;

import online.fimbi.Dto.BondDto;
import online.fimbi.Dto.BondDtoRes;
import online.fimbi.Dto.PurchaseDto;
import online.fimbi.Services.BondService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bonds")
public class BondController {
	@Autowired
	BondService bondService;

	@PostMapping
	public ResponseEntity<BondDto> post_bond(@RequestBody BondDto bondDto) {
		bondService.save_bond(bondDto);
		return new ResponseEntity<>(bondDto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<BondDto>> latest_Bonds() {
		List<BondDto> latest_bonds = bondService.getBonds();
		return new ResponseEntity<>(latest_bonds, HttpStatus.OK);
	}

	@GetMapping("{bond_id}")
	public ResponseEntity<BondDtoRes> getById(@PathVariable Long bond_id) {
		BondDtoRes bondDtoRes = bondService.getById(bond_id);
		return new ResponseEntity<>(bondDtoRes, HttpStatus.OK);
	}

	@GetMapping("user/{username}")
	public ResponseEntity<List<BondDto>> getBondsByUsername(@PathVariable String username) {
		List<BondDto> bondsDto = bondService.getBondsByUsername(username);
		return new ResponseEntity<>(bondsDto, HttpStatus.OK);
	}

	@GetMapping("issuer/{identifier}")
	public ResponseEntity<List<BondDto>> getBondsByIssuer(@PathVariable String identifier) {
		List<BondDto> bondsDto = bondService.getBondsByIdentifier(identifier);
		return new ResponseEntity<>(bondsDto, HttpStatus.OK);
	}

	@GetMapping("/latest/{size}") // ask for n (5)
	public ResponseEntity<List<BondDto>> latest_Bonds(@PathVariable int size) {
		List<BondDto> latest_bonds = bondService.get_lastest_bonds(size);
		return new ResponseEntity<>(latest_bonds, HttpStatus.OK);
	}

	@GetMapping("purchases/{size}")
	public ResponseEntity<List<PurchaseDto>> latest_purchases(@PathVariable int size) {
		List<PurchaseDto> latest_purchases = bondService.get_lastest_purchases(size);
		return new ResponseEntity<>(latest_purchases, HttpStatus.OK);
	}
}
