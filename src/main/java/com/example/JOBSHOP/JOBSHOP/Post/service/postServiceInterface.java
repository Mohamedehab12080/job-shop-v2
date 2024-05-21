package com.example.JOBSHOP.JOBSHOP.Post.service;

import java.util.List;
import java.util.Set;

import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.Specifications.postSearch;

public interface postServiceInterface {

	List<Post> findPostsWithSearch(postSearch postSearch);

	Post getReferenceById(Long id);

	List<Post> findAll();

	Post insert(Post t);

	Post findById(Long id);

	postDTO update(Long postId,postDTO t);

	Post updateWithId(Long id, Post t);

	List<Post> insertAll(List<Post> entity);

	void deleteById(Long id);

	List<Post> findByEmployer(Long id);

	List<Post> findByCompanyProfile(Long id);

	Set<Post> findPostsWithTitle(String title);

	List<Post> findByEmployerId(Long id);

	Post findPostsWithTitleOne(String title);
	

	List<Post> findPostsWithFieldName(String fieldName);

	void updatePostForCreate(Post post);
}
