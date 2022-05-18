package online.fimbi.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online.fimbi.Dto.Bond;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("test")
public class Test {
	@GetMapping("bond")
	public ResponseEntity<Bond> get_bond() {
		return new ResponseEntity<>(Bond.random_bond(), HttpStatus.OK);
	}
}
