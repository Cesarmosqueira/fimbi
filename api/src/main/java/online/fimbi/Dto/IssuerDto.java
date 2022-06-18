package online.fimbi.Dto;

import java.nio.charset.Charset;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssuerDto {
	Long id;
	String market_identifier; // (ISO 10383)
	String description;
	String image_url;

	private static String getAlphaNumericString(int n) {

		// length is bounded by 256 Character
		byte[] array = new byte[256];
		new Random().nextBytes(array);

		String randomString = new String(array, Charset.forName("UTF-8"));

		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer();

		// Append first 20 alphanumeric characters
		// from the generated random String into the result
		for (int k = 0; k < randomString.length(); k++) {

			char ch = randomString.charAt(k);

			if (((ch >= 'a' && ch <= 'z')
					|| (ch >= 'A' && ch <= 'Z'))
					&& (n > 0)) {

				r.append(ch);
				n--;
			}
			if (n <= 0)
				break;
		}

		// return the resultant string
		return r.toString();
	}

	public static IssuerDto random_issuer() {
		String mic = getAlphaNumericString(4);

		String des = "This is a random generated Issuer.";
		String img = "https://www.kindpng.com/picc/m/231-2310451_building-clip-art-images-free-clipart-images-building.png";

		IssuerDto issuerDto = new IssuerDto(Long.parseLong("0"), mic, des, img);
		return issuerDto;

	}
}
