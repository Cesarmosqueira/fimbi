package online.fimbi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import online.fimbi.Dto.FimbiResponse;
import online.fimbi.Dto.LoginRequest;
import online.fimbi.Dto.UserDto;
import online.fimbi.Exception.FimbiException;
import online.fimbi.Services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("signup")
	public ResponseEntity<FimbiResponse> signup(@RequestBody UserDto userDto) throws FimbiException {
		FimbiResponse response;
		try {
			response = userService.register_user(userDto);
		} catch (Exception e) {
			throw new FimbiException("User or email already exists");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("login")
	public ResponseEntity<FimbiResponse> login(@RequestBody LoginRequest loginRequest) throws FimbiException {
		FimbiResponse response = userService.login_user(loginRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("adquire_bond")
	public ResponseEntity<FimbiResponse> adquire_bond(@RequestBody LoginRequest loginRequest,
			@RequestParam String bond_id) throws FimbiException {
		FimbiResponse login_response = userService.login_user(loginRequest);
		if (login_response.getCode() == 0) {
			throw new FimbiException("Couldn't authenticate");
		}
		FimbiResponse purchase_response = userService.adquire_bond(loginRequest.getUsername(), Long.valueOf(bond_id));
		return new ResponseEntity<>(purchase_response, HttpStatus.OK);
	}
}
