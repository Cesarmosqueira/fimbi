package online.fimbi.Services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.fimbi.Common.EntityDtoConverter;
import online.fimbi.Dto.FimbiResponse;
import online.fimbi.Dto.LoginRequest;
import online.fimbi.Dto.UserDto;
import online.fimbi.Entities.Bond;
import online.fimbi.Entities.User;
import online.fimbi.Entities.UserxBond;
import online.fimbi.Exception.FimbiException;
import online.fimbi.Repositories.BondRepository;
import online.fimbi.Repositories.UserRepository;
import online.fimbi.Repositories.UserxbondRepository;

@Transactional
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserxbondRepository userxbondRepository;

	@Autowired
	BondRepository bondRepository;

	@Autowired
	EntityDtoConverter entityDtoConverter;

	public FimbiResponse register_user(UserDto userDto) {
		User user = new User(userDto);
		userRepository.save(user);
		return new FimbiResponse("success", 1);

	}

	public User userByUsername(String username) throws FimbiException {
		return userRepository.getByUsername(username)
				.orElseThrow(() -> new FimbiException(username + " not found"));
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

	public FimbiResponse adquire_bond(String username, Long bond_id) throws FimbiException {

		int bond_occurences = userxbondRepository.getBondOccurrences(bond_id);
		Bond bond = bondRepository.findById(bond_id)
				.orElseThrow(() -> new FimbiException("bond doesn't exist"));
		int bond_splits = bond.getSplits();
		if (bond_occurences + 1 <= bond_splits || bond.getAvailable() == 1) {
			User u = userRepository.getByUsername(username)
					.orElseThrow(() -> new FimbiException(username + " not found"));

			UserxBond relation = new UserxBond();
			relation.setBond_id(bond.getId());
			relation.setUser_id(u.getId());
			relation.setPurchase_date(new Date());
			userxbondRepository.save(relation);
			bond_occurences += 1;

			// disable if neccesary
			if (bond_occurences == bond.getSplits()) {
				bondRepository.disable_bond(bond.getId());
			}

			return new FimbiResponse(bond.getSplits() - bond_occurences + "/" + bond.getSplits()
					+ " instances left for bond #" + bond.getId(), 1);
		}
		throw new FimbiException("There are no instances left for bond #" + bond.getId());
	}

	public UserDto getUserByUsername(String username) {
		User user = userRepository.getByUsername(username)
				.orElseThrow(() -> new FimbiException("'" + username + "' not found"));
		return entityDtoConverter.convertUserToDto(user);
	}
}
