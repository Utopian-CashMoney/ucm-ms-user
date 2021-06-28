// package com.ucm.ms.dao;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.util.Date;

// import javax.transaction.Transactional;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.ucm.ms.entity.ConfirmToken;
// import com.ucm.ms.entity.User;


// // Class for testing Dao Layer

// @SpringBootTest
// @Transactional
// class ConfirmationTokenRepositoryTest {
	
// 	  @Autowired
// 	   private ConfirmationTokenRepository tokenDao;
	  
// 	  @Autowired
// 	   private UserRepository userDao;
		

// 	@Test
// 	void testFindByUserName() {
		
// 		Date date = new Date(0);
		
// 		User user = new User();
// 		user.setUsername("name1");
// 		user.setEmail("name1@gmail.com");
// 		user.setPassword("Name1");
// 		user.setPhNum("111-111-1111");
// 		user.setFirstName("name1");
// 		user.setLastName("one");
// 		user.setIsActive(true);
		
// 		userDao.saveAndFlush(user);

		
// 		ConfirmToken confirmToken = new ConfirmToken();
// 		confirmToken.setConfirmationToken("12csdvsdvhb3bhhvhg3vgjvh3jkvhj3v");
// 		confirmToken.setCreatedDate(date);
// 		confirmToken.setUser(user);
		
// 		tokenDao.saveAndFlush(confirmToken);
		
// 		assertEquals("12csdvsdvhb3bhhvhg3vgjvh3jkvhj3v", tokenDao.findByConfirmationToken("12csdvsdvhb3bhhvhg3vgjvh3jkvhj3v").getConfirmationToken());
		
// 		// Tests with different confirmation token
// 		confirmToken.setConfirmationToken("dsfssfdfsd3sxaxasc1132fcd");
// 		assertEquals("dsfssfdfsd3sxaxasc1132fcd", tokenDao.findByConfirmationToken("dsfssfdfsd3sxaxasc1132fcd").getConfirmationToken());		
		
// 		// Tests with different confirmation token
// 		confirmToken.setConfirmationToken("aoakncininuh1jibuv3gvyv2yyv");
// 		assertEquals("aoakncininuh1jibuv3gvyv2yyv", tokenDao.findByConfirmationToken("aoakncininuh1jibuv3gvyv2yyv").getConfirmationToken());	
		
// 	}

// }
