package com.likelion.stepstone.post;

import com.likelion.stepstone.post.model.PostDto;
import com.likelion.stepstone.post.model.PostEntity;
import com.likelion.stepstone.post.model.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

public class PostService {
    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public void create(PostDto postDto) {
        PostEntity postEntity = PostEntity.toEntity(postDto);
        postEntity.setPostId(UUID.randomUUID());
        postEntity.setLikes(0);

        postRepository.save(postEntity);
    }


     public void edit(PostDto postDto) {
        PostEntity postEntity = postRepository.findByPostId(postDto.getPostId());
        postEntity.setTitle(postDto.getTitle());
        postEntity.setBody(postDto.getBody());
        postRepository.save(postEntity);


  }

    public void delete(PostDto postDto) {
        PostEntity postEntity = postRepository.findByPostId(postDto.getPostId());
        postRepository.delete(postEntity);
    }

    public Page<PostVo> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return postRepository.findAll(pageable).map(post -> PostVo.toVo(PostDto.toDto(post)));
    }

    public List<PostVo> getPostList() {
        List<PostEntity> postEntities = postRepository.findAll();
        return postEntities.stream().map(postEntity -> PostVo.toVo(PostDto.toDto(postEntity))).collect(Collectors.toList());
    }

}