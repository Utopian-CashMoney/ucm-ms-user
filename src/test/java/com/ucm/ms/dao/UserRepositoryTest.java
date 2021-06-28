// package com.ucm.ms.dao;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import javax.transaction.Transactional;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.ucm.ms.entity.User;


// // Class for testing Dao Layer

// @SpringBootTest
// @Transactional
// class UserRepositoryTest {
	
// 	  @Autowired
// 	   private UserRepository userDao;
		

// 	@Test
// 	void testFindByUserName() {
		
// 		User user = new User();
// 		user.setUsername("name1");
// 		user.setEmail("name1@gmail.com");
// 		user.setPassword("Name1");
// 		user.setPhNum("111-111-1111");
// 		user.setFirstName("name1");
// 		user.setLastName("one");
// 		user.setIsActive(true);
		
// 		userDao.saveAndFlush(user);
		
// 		assertEquals("name1", userDao.findByUsername("name1").get().getUsername());
		
// 		// Tests with different username
// 		user.setUsername("name2");
// 		assertEquals("name2", userDao.findByUsername("name2").get().getUsername());
		
// 		// Tests with different username
// 		user.setUsername("name3");
// 		assertEquals("name3", userDao.findByUsername("name3").get().getUsername());
		
// 	}

// }
