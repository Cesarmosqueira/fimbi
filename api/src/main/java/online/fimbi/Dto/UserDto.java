package online.fimbi.Dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {
	String username;
	String email;
	String password;
	Date birth_date;
	String image_url;
}
