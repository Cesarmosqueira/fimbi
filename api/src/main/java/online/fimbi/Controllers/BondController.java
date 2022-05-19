package online.fimbi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		System.out.println("Starting");
		bondService.save_bond(bondDto);
		System.out.println("Done");
		return new ResponseEntity<>(bondDto, HttpStatus.OK);
	}
}
