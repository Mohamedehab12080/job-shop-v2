package com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.Follow;

@Repository
public interface followRepository extends /*baseRepo<Follow, Long>*/ JpaRepository<Follow,Long>{

//	@Query(value="Select * from Follow f where f.follower_id=?1")
	List<Follow> findByFollower(User user);
//	@Query(value="Select * from Follow f where f.following_id=?1")
	List<Follow> findByFollowing(User user);
}
