package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.models.Follow;
import com.example.JOBSHOP.JOBSHOP.models.User;


@Repository
public interface followRepository extends JpaRepository<Follow, Long>{

//	@Query(value="Select * from Follow f where f.follower_id=?1")
	List<Follow> findByFollower(User user);
//	@Query(value="Select * from Follow f where f.following_id=?1")
	List<Follow> findByFollowing(User user);
}
