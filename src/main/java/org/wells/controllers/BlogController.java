package org.wells.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wells.mockDb.BlogData;
import org.wells.models.Blog;
import org.wells.repository.BlogRepository;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class BlogController {
    @Autowired
    BlogRepository blogRepository;
    Random random = new Random();

    //if you dont want to setup database and mock db uncomment below line
    //BlogData blogData = BlogData.getInstance();

    @GetMapping("/blog")
    public List<Blog> index(){
        //return  blogData.fetchBlogs();
        return blogRepository.findAll();
    }

    @GetMapping("/blog/{id}")
    public Blog showBlog(@PathVariable String id){
        int blogId = Integer.parseInt(id);

        //return blogData.getBlogById(blogId);
        return blogRepository.findOne(blogId);
    }

    @PostMapping("/blog/search")
    public List<Blog> serach(@RequestBody Map<String, String> body){
        String words = body.get("text");

        //return blogData.searchBlogs(words);
        return blogRepository.findByTitleContainingOrContentContaining(words, words);
    }

    @PostMapping("/blog")
    public Blog create(@RequestBody Map<String, String> body){
        //int id = random.nextInt(1000);
        String title = body.get("title");
        String content = body.get("content");

        //return blogData.craeteBlog(id, title, content);
        return blogRepository.save(new Blog(title, content));
    }

    @PutMapping("/blog/{id}")
    public Blog update(@PathVariable String id, @RequestBody Map<String, String> body){
        int blogId = Integer.parseInt(id);
        String title = body.get("title");
        String content = body.get("content");

        Blog blog = blogRepository.findOne(blogId);
        blog.setTitle(title);
        blog.setContent(content);

        //return blogData.updateBlog(blogId, title, content);
        return blogRepository.save(blog);
    }

    @DeleteMapping("/blog/{id}")
    public boolean delete(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        blogRepository.delete(blogId);

        //return blogData.delete(blogId);
        return true;
    }
}
