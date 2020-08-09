package com.poll.app.repos;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poll.app.data.UserData;


public interface UserRepos extends JpaRepository<UserData, Integer> {
	@Query(value="select * from poll_admin where login_id= :login_id and password= :password", nativeQuery=true)
	UserData getUserDetails(@Param("login_id") String login_id,@Param("password") String password);
	
	@Query(value="select id from poll_admin where login_id= :login_id", nativeQuery=true)
	String getUserId(@Param("login_id") String login_id);
	
	@Query(value="select password from poll_admin where login_id= :login_id", nativeQuery=true)
	String getPassword(@Param("login_id") String login_id);
	
	@Modifying
	@Query(value="update poll_admin set session_index = :session_index where id= :id", nativeQuery=true)
	@Transactional
	int updateUserSession(@Param("session_index") String session_index,@Param("id") int id);
	
	@Modifying
	@Query(value="update poll_admin set session_index = :defSession, login_success='N' where session_index= :session_index and id= :id", nativeQuery=true)
	@Transactional
	int updateUserSessionOut(@Param("defSession") String defSession,@Param("session_index") String session_index,@Param("id") int id);
	
}
