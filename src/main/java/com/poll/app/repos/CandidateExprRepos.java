package com.poll.app.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poll.app.data.CandidateExpertArea;

public interface CandidateExprRepos extends JpaRepository<CandidateExpertArea, Integer> {
	@Query(value="select * from candidate_expert_area where deleted= 'N' and candidate_id= :candidate_id", nativeQuery=true)
	List<CandidateExpertArea> getCandidateList(@Param("candidate_id") int candidate_id);
		
	
	@Modifying
	@Query(value="update candidate_expert_area set hackername = :hackername,noOfChallenge = :noOfChallenge,explevel = :explevel where id= :id", nativeQuery=true)
	@Transactional
	int updatesUserLabel(@Param("hackername") String hackername,@Param("noOfChallenge") String noOfChallenge,@Param("explevel") String explevel,@Param("id") int id);

	@Modifying
	@Query(value="update candidate_expert_area set deleted = 'Y' where candidate_id= :candidate_id", nativeQuery=true)
	@Transactional
	int updateDelete(@Param("candidate_id") int candidate_id);
	
	@Modifying
	@Query(value="update candidate_expert_area set deleted = 'Y' where candidate_id= :candidate_id and id= :id", nativeQuery=true)
	@Transactional
	int deleteSelected(@Param("candidate_id") int candidate_id,@Param("id") int id);
	
	@Modifying
	@Query(value="delete from candidate_expert_area where candidate_id= :candidate_id", nativeQuery=true)
	@Transactional
	int deleteAll(@Param("candidate_id") int candidate_id);
	
}
