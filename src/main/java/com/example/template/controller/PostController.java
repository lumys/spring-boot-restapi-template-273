package com.example.template.controller;

import com.example.template.domain.CommonResult;
import com.example.template.domain.ListResult;
import com.example.template.domain.SingleResult;
import com.example.template.entity.PostEntity;
import com.example.template.exception.NotFoundException;
import com.example.template.repository.PostRepository;
import com.example.template.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
public class PostController {

    private final PostRepository postRepository;
    private final ResponseService responseService;  // 결과 처리용 Service

    @GetMapping
    public ListResult<PostEntity> findAll() {
        return responseService.getListResult(postRepository.findAll());
    }

    @GetMapping("/{postSeq}")
    public SingleResult<PostEntity> findById(@PathVariable Long postSeq) {
        return responseService.getSingleResult(postRepository
            .findById(postSeq)
            .orElseThrow(NotFoundException::new));
    }

    @PostMapping
    public CommonResult save(@RequestBody PostEntity postEntity) {
        postRepository.save(postEntity);
        return responseService.getSuccessResult();
    }

    @PutMapping("/{postSeq}")
    public CommonResult saveById(
        @PathVariable Long postSeq,
        @RequestBody PostEntity postEntity
    ) {
        postEntity.setPostSeq(postSeq);
        postRepository.save(postEntity);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/{postSeq}")
    public CommonResult deleteById(
        @PathVariable Long postSeq
    ) {
        postRepository.deleteById(postSeq);
        return responseService.getSuccessResult();
    }
}
