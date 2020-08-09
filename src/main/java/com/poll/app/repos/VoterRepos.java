package com.poll.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poll.app.data.VoterData;

public interface VoterRepos extends JpaRepository<VoterData, Integer> {

	@Query(value="select voter_ip from voter_details where voter_ip= :voter_ip and candidate_id= :candidate_id", nativeQuery=true)
	String getVote(@Param("voter_ip") String voterIp,@Param("candidate_id") int candidate_id);
}
