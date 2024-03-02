package com.example.JOBSHOP.JOBSHOP.Follow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.User.User;

@Repository
public interface followRepository extends /*baseRepo<Follow, Long>*/ JpaRepository<Follow,Long>{

//	@Query(value="Select * from Follow f where f.follower_id=?1")
	List<Follow> findByFollower(User user);
//	@Query(value="Select * from Follow f where f.following_id=?1")
	List<Follow> findByFollowing(User user);
}
