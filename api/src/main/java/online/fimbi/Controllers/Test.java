package online.fimbi.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("test")
public class Test {
	@GetMapping
	public ResponseEntity<String> homla() {
		return new ResponseEntity<>("Homla", HttpStatus.OK);
	}

}
