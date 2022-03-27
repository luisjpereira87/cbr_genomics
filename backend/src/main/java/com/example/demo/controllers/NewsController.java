package com.example.demo.controllers;

import com.example.demo.constants.MessagesContants;
import com.example.demo.dto.NewsDTO;
import com.example.demo.exceptions.CustomException;
import com.example.demo.models.News;
import com.example.demo.services.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@SecurityRequirement(name = "cbr_genomics")
public class NewsController {

  @Autowired
  private NewsService newsService;


  @PostMapping
  @ResponseBody
  @Secured({MessagesContants.ROLE_ADMIN, MessagesContants.ROLE_USER})
  @Operation(summary = "Create new news", description = "Create new news")
  public ResponseEntity<News> create(@RequestBody NewsDTO newsDTO) throws CustomException {
    return ResponseEntity.ok(this.newsService.save(newsDTO));
  }

  @GetMapping("/{id}")
  @ResponseBody
  @Secured({MessagesContants.ROLE_ADMIN, MessagesContants.ROLE_USER})
  @Operation(summary = "Get news by id", description = "Get the news by id and validate if the logged in user owns it")
  public ResponseEntity<News> get(@PathVariable("id") Long id) throws CustomException {
    return ResponseEntity.ok(this.newsService.get(id));
  }

  @GetMapping("/in-draft")
  @ResponseBody
  @Secured({MessagesContants.ROLE_ADMIN})
  @Operation(summary = "Get news list in draft", description = "Get the news list in draft and validate if user logged is administrator")
  public ResponseEntity<List<News>> getInDraft() throws CustomException {
    return ResponseEntity.ok(this.newsService.getInDraft());
  }

  @PutMapping("/upvote/{id}")
  @ResponseBody
  @Secured({MessagesContants.ROLE_ADMIN, MessagesContants.ROLE_USER})
  @Operation(summary = "Vote for news by id", description = "Vote for news by id")
  public ResponseEntity<News> upvote(@PathVariable("id") Long id) throws CustomException {
    return ResponseEntity.ok(this.newsService.upvote(id));
  }
}
