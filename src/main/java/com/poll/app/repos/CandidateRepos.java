package com.poll.app.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poll.app.data.CandidateData;

public interface CandidateRepos extends JpaRepository<CandidateData, Integer> {
	@Query(value="select * from candidate_list where deleted= 'N' order by hackername desc", nativeQuery=true)
	List<CandidateData> getCandidateList();
	
	@Query(value="select vote from candidate_list where deleted= 'N' and id= :id", nativeQuery=true)
	int getVote(@Param("id") int id);
	
	@Modifying
	@Query(value="update candidate_list set hackername = :hackername,solved_challanges = :solved_challanges,explevel = :explevel where id= :id", nativeQuery=true)
	@Transactional
	int updatesUserLabel(@Param("hackername") String hackername,@Param("solved_challanges") Integer solved_challanges,@Param("explevel") Integer explevel,@Param("id") int id);

	@Modifying
	@Query(value="update candidate_list set deleted = 'Y' where id= :id", nativeQuery=true)
	@Transactional
	int deleteCandidate(@Param("id") int id);
	
	@Modifying
	@Query(value="update candidate_list set vote = :vote where id= :id", nativeQuery=true)
	@Transactional
	int voteToCandidate(@Param("vote") int vpte,@Param("id") int id);
	
}
