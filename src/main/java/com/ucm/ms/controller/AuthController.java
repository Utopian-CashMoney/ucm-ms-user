package com.ucm.ms.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ucm.ms.config.JwtUtils;
import com.ucm.ms.dao.ConfirmationTokenRepository;
import com.ucm.ms.dao.UserRepository;
import com.ucm.ms.dto.RequestLoginDto;
import com.ucm.ms.dto.RequestSignupDto;
import com.ucm.ms.dto.ResponseLoginDto;
import com.ucm.ms.entity.ConfirmToken;
import com.ucm.ms.entity.User;
import com.ucm.ms.service.UserInfo;
import com.ucm.ms.service.UserInfoService;

/**
 * Controller layer, which handles the API requests from the users
 * 
 * @author Charvin Patel
 */

@CrossOrigin

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserInfoService userInfoService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	// Account Verification Link expiry time in milliseconds( 1 min = 60,000 ms )
	final int linkExpiryTime = 180000;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	/**
	 * 
	 * @param userRequest
	 * @return ResponseEntity<?>
	 * 
	 */

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody RequestLoginDto userRequest) {
		if (userRepository.existsByUsername(userRequest.getUsername())) {
			
			User user = userRepository.findByUsername(userRequest.getUsername());

			Boolean isActive = user.getisActive();

			if (!isActive) {
				throw new Error("Please confirm the account first via email sent to you!");
			}

		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtUtils.generateJwtToken(authentication);

		UserInfo userDetails = (UserInfo) authentication.getPrincipal();

		return ResponseEntity.ok(new ResponseLoginDto(jwtToken, 
				userDetails.getId(), 
				userDetails.getUsername(), 
				userDetails.getEmail(),
				userDetails.getPhNum(),
				userDetails.getFirstName(),
				userDetails.getLastName(),
				userDetails.getAddress(),
				userDetails.getCity(),
				userDetails.getState(),
				userDetails.getZipcode()));

	}

	/**
	 * 
	 * @param userRequest
	 * 
	 */

	@PostMapping("/signup")
	public void signupUser(@RequestBody RequestSignupDto userRequest) {

		User user = new User();
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setPhNum(userRequest.getPhone());
		user.setFirstName(userRequest.getFirst_name());
		user.setLastName(userRequest.getLast_name());
		user.setAddress(userRequest.getAddress());
		user.setCity(userRequest.getCity());
		user.setState(userRequest.getState());
		user.setZipcode(userRequest.getZipcode());

		userRepository.save(user);

		ConfirmToken confirmationToken = new ConfirmToken(user);

		confirmationTokenRepository.save(confirmationToken);

		String to = user.getEmail();
		String from = "utopiacashmoney99@gmail.com";
		final String username = "utopiacashmoney99@gmail.com";
		final String password = "UtopiaBanking100?";

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("Registration Confirmation!");
			message.setContent("<html>\n" + "\n" + "<head>\n" + "    <title></title>\n"
					+ "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n"
					+ "    <style type=\"text/css\">\n" + "        @media screen {\n" + "            @font-face {\n"
					+ "                font-family: 'Lato';\n" + "                font-style: normal;\n"
					+ "                font-weight: 400;\n"
					+ "                src: local('Lato Regular'), local('Lato-Regular'), url(https://fonts.gstatic.com/s/lato/v11/qIIYRU-oROkIk8vfvxw6QvesZW2xOQ-xsNqO47m55DA.woff) format('woff');\n"
					+ "            }\n" + "\n" + "            @font-face {\n" + "                font-family: 'Lato';\n"
					+ "                font-style: normal;\n" + "                font-weight: 700;\n"
					+ "                src: local('Lato Bold'), local('Lato-Bold'), url(https://fonts.gstatic.com/s/lato/v11/qdgUG4U09HnJwhYI-uK18wLUuEpTyoUstqEm5AMlJo4.woff) format('woff');\n"
					+ "            }\n" + "\n" + "            @font-face {\n" + "                font-family: 'Lato';\n"
					+ "                font-style: italic;\n" + "                font-weight: 400;\n"
					+ "                src: local('Lato Italic'), local('Lato-Italic'), url(https://fonts.gstatic.com/s/lato/v11/RYyZNoeFgb0l7W3Vu1aSWOvvDin1pK8aKteLpeZ5c0A.woff) format('woff');\n"
					+ "            }\n" + "\n" + "            @font-face {\n" + "                font-family: 'Lato';\n"
					+ "                font-style: italic;\n" + "                font-weight: 700;\n"
					+ "                src: local('Lato Bold Italic'), local('Lato-BoldItalic'), url(https://fonts.gstatic.com/s/lato/v11/HkF_qI1x_noxlxhrhMQYELO3LdcAZYWl9Si6vvxL-qU.woff) format('woff');\n"
					+ "            }\n" + "        }\n" + "\n" + "        /* CLIENT-SPECIFIC STYLES */\n"
					+ "        body,\n" + "        table,\n" + "        td,\n" + "        a {\n"
					+ "            -webkit-text-size-adjust: 100%;\n" + "            -ms-text-size-adjust: 100%;\n"
					+ "        }\n" + "\n" + "        table,\n" + "        td {\n"
					+ "            mso-table-lspace: 0pt;\n" + "            mso-table-rspace: 0pt;\n" + "        }\n"
					+ "\n" + "        img {\n" + "            -ms-interpolation-mode: bicubic;\n" + "        }\n" + "\n"
					+ "        /* RESET STYLES */\n" + "        img {\n" + "            border: 0;\n"
					+ "            height: auto;\n" + "            line-height: 100%;\n"
					+ "            outline: none;\n" + "            text-decoration: none;\n" + "        }\n" + "\n"
					+ "        table {\n" + "            border-collapse: collapse !important;\n" + "        }\n" + "\n"
					+ "        body {\n" + "            height: 100% !important;\n"
					+ "            margin: 0 !important;\n" + "            padding: 0 !important;\n"
					+ "            width: 100% !important;\n" + "        }\n" + "\n" + "        /* iOS BLUE LINKS */\n"
					+ "        a[x-apple-data-detectors] {\n" + "            color: inherit !important;\n"
					+ "            text-decoration: none !important;\n" + "            font-size: inherit !important;\n"
					+ "            font-family: inherit !important;\n"
					+ "            font-weight: inherit !important;\n"
					+ "            line-height: inherit !important;\n" + "        }\n" + "\n"
					+ "        /* MOBILE STYLES */\n" + "        @media screen and (max-width:600px) {\n"
					+ "            h1 {\n" + "                font-size: 32px !important;\n"
					+ "                line-height: 32px !important;\n" + "            }\n" + "        }\n" + "\n"
					+ "        /* ANDROID CENTER FIX */\n" + "        div[style*=\"margin: 16px 0;\"] {\n"
					+ "            margin: 0 !important;\n" + "        }\n" + "    </style>\n" + "</head>\n" + "\n"
					+ "<body style=\"background-color: #f4f4f4; margin: 0 !important; padding: 0 !important;\">\n"
					+ "    <!-- HIDDEN PREHEADER TEXT -->\n"
					+ "    <div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: 'Lato', Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\"> We're thrilled to have you here! Get ready to dive into your new account. </div>\n"
					+ "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
					+ "        <!-- LOGO -->\n" + "        <tr>\n"
					+ "            <td bgcolor=\"#FFA73B\" align=\"center\">\n"
					+ "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
					+ "                    <tr>\n"
					+ "                        <td align=\"center\" valign=\"top\" style=\"padding: 40px 10px 40px 10px;\"> </td>\n"
					+ "                    </tr>\n" + "                </table>\n" + "            </td>\n"
					+ "        </tr>\n" + "        <tr>\n"
					+ "            <td bgcolor=\"#FFA73B\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n"
					+ "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
					+ "                    <tr>\n"
					+ "                        <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;\">\n"
					+ "                            <h1 style=\"font-size: 48px; font-weight: 400; margin: 2;\">Welcome!</h1> <img src=\" https://img.icons8.com/clouds/100/000000/handshake.png\" width=\"125\" height=\"120\" style=\"display: block; border: 0px;\" />\n"
					+ "                        </td>\n" + "                    </tr>\n" + "                </table>\n"
					+ "            </td>\n" + "        </tr>\n" + "        <tr>\n"
					+ "            <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n"
					+ "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n"
					+ "                    <tr>\n"
					+ "                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 40px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n"
					+ "                            <p style=\"margin: 0;\">We're excited to have you here. First, you need to confirm your account. Just press the button below.</p>\n"
					+ "                        </td>\n" + "                    </tr>\n" + "                    <tr>\n"
					+ "                        <td bgcolor=\"#ffffff\" align=\"left\">\n"
					+ "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
					+ "                                <tr>\n"
					+ "                                    <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 20px 30px 60px 30px;\">\n"
					+ "                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
					+ "                                            <tr>\n"
					+ "                                                <td align=\"center\" style=\"border-radius: 3px;\" bgcolor=\"#FFA73B\"><a href=http://localhost:8080/auth/confirm-account?token="
					+ confirmationToken.getConfirmationToken()
					+ " target=\"_blank\" style=\"font-size: 20px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; color: #ffffff; text-decoration: none; padding: 15px 25px; border-radius: 2px; border: 1px solid #FFA73B; display: inline-block;\">Confirm Account</a></td>\n"
					+ "                                            </tr>\n"
					+ "                                        </table>\n"
					+ "                                    </td>\n" + "                                </tr>\n"
					+ "                            </table>\n" + "                        </td>\n"
					+ "                    </tr> <!-- COPY -->\n" + "                    <tr>\n"
					+ "                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 0px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n"
					+ "                            <p style=\"margin: 0;\">If that doesn't work, copy and paste the following link in your browser:</p>\n"
					+ "                        </td>\n" + "                    </tr> <!-- COPY -->\n"
					+ "                    <tr>\n"
					+ "                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 20px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n"
					+ "                            <p style=\"margin: 0;\"><a href=http://localhost:8080/auth/confirm-account?token="
					+ confirmationToken.getConfirmationToken() + ">http://localhost:8080/auth/confirm-account?token="
					+ confirmationToken.getConfirmationToken() + "</a></p>\n" + "                        </td>\n"
					+ "                    </tr>\n" + "                    <tr>\n"
					+ "                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 40px 30px; border-radius: 0px 0px 4px 4px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n"
					+ "                            <p style=\"margin: 0;\">Cheers,<br>Utopia CashMoney</p>\n"
					+ "                        </td>\n" + "                    </tr>\n" + "                </table>\n"
					+ "            </td>\n" + "        </tr>\n" + "    </table>\n" + "</body>\n" + "\n" + "</html>",
					"text/html");

			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param modelAndView, confirmationToken
	 * @return ModelAndView
	 * 
	 */

	@RequestMapping(path = "/confirm-account")
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
		ConfirmToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		Calendar cal = Calendar.getInstance();

		if (((token.getCreatedDate().getTime() + linkExpiryTime) - cal.getTime().getTime()) <= 0) {

			modelAndView.setViewName("emailLinkExpired");
		}

		else {

			if (token != null) {
				User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
				user.setIsActive(true);

				userRepository.save(user);

				modelAndView.setViewName("emailActivated");
			}

		}

		return modelAndView;
	}
	
	@PostMapping(path = "/updateProfile")
	public void updateUserProfile(@RequestBody RequestSignupDto signupDto) {
		User user = userRepository.findById(signupDto.getId());
		
		// User will only be able to edit email, phone, address, city, state, zipcode in "User profile"
		user.setEmail(signupDto.getEmail());
		user.setPhNum(signupDto.getPhone());
		user.setAddress(signupDto.getAddress());
		user.setCity(signupDto.getCity());
		user.setState(signupDto.getState());
		user.setZipcode(signupDto.getZipcode());
		
		userRepository.save(user);
		
	}
	
	@GetMapping(path = "/getUser")
	public ResponseEntity<User> get(@RequestParam String userId) {
		try {
			int id = Integer.valueOf(userId);
			User user = userInfoService.getUser(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Get all Users in DB
	 * 
	 * @return Collection of all users
	 */
	@GetMapping("/users")
	public ResponseEntity<Collection<User>> get() {
		try {
			Collection<User> users = userRepository.findAll();
			return new ResponseEntity<>(users, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
