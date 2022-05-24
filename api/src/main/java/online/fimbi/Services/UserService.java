package online.fimbi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.fimbi.Dto.FimbiResponse;
import online.fimbi.Dto.LoginRequest;
import online.fimbi.Dto.UserDto;
import online.fimbi.Entities.User;
import online.fimbi.Exception.FimbiException;
import online.fimbi.Repositories.UserRepository;

@Transactional
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public FimbiResponse register_user(UserDto userDto) {
		User user = new User(userDto);
		userRepository.save(user);
		return new FimbiResponse("success", 1);

	}

	public FimbiResponse login_user(LoginRequest loginRequest) throws FimbiException {
		String username = loginRequest.getUsername();
		String email = loginRequest.getEmail();

		String password = null;
		if (username == null && email == null) {
			throw new FimbiException("No data received");
		} else if (!username.equals("-1")) {
			password = userRepository.get_password_username(username)
					.orElseThrow(() -> new FimbiException("User doesn't exist"));
		} else if (!email.equals("-1")) {
			password = userRepository.get_password_email(email)
					.orElseThrow(() -> new FimbiException("User doesn't exist"));
		}

		if (password.equals(loginRequest.getPassword())) {
			return new FimbiResponse("Login successfull", 1);
		} else {
			return new FimbiResponse("Login failed", 0);
		}
	}
}
