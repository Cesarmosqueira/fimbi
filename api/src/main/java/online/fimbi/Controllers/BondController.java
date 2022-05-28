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

	@GetMapping("/latest/{size}") // ask for n (5)
	public ResponseEntity<List<BondDto>> latest_Bonds(@PathVariable int size) {
		List<BondDto> latest_bonds = bondService.get_lastest_bonds(size);
		return new ResponseEntity<>(latest_bonds, HttpStatus.OK);
	}
}
