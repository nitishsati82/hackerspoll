package com.poll.app.repos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poll.app.data.ExpertAreaData;


public interface ExptAreaRepos extends JpaRepository<ExpertAreaData, Integer> {

	@Query(value="select * from expert_area where deleted='N'", nativeQuery=true)
	List<ExpertAreaData> getExpertAreas();
	
	@Query(value="select * from expert_area where deleted='N' and candidate_id= :candidate_id", nativeQuery=true)
	List<ExpertAreaData> getAllExpertAreas(@Param("candidate_id") int id);
	
	@Query(value="select expertarea,deleted,id from expert_area", nativeQuery=true)
	ExpertAreaData getExpertArea();
	
	@Query(value="select expertarea,deleted,id from expert_area where expertarea= :expertarea COLLATE NOCASE", nativeQuery=true)
	ExpertAreaData matchExpertArea(@Param("expertarea") String expertarea);
	
	@Modifying
	@Query(value="update expert_area set expertarea = :expertarea where id= :id", nativeQuery=true)
	@Transactional
	int updateExprtArea(@Param("expertarea") String expertarea,@Param("id") int id);
	
	@Modifying
	@Query(value="update expert_area set deleted = 'Y' where id= :id", nativeQuery=true)
	@Transactional
	int deleteExprtArea(@Param("id") int id);
}
