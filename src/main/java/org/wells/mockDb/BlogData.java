package org.wells.mockDb;

import org.wells.models.Blog;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BlogData {
    private List<Blog> blogs;
    public static BlogData instance = null;

    public static  BlogData getInstance(){
        if(instance==null){
            instance = new BlogData();
        }

        return instance;
    }

    public BlogData(){
        blogs = new ArrayList<Blog>();
        blogs.add(new Blog(1, "First Blog", "Content of First Blog"));
        blogs.add(new Blog(2, "Second Blog", "Content of Second Blog"));
    }

    public List<Blog> fetchBlogs() {
        return blogs;
    }

    public Blog getBlogById(int id) {
        ListIterator<Blog> blogListIterator = blogs.listIterator();

        while(blogListIterator.hasNext()){
            Blog temp = blogListIterator.next();
            if(temp.getId()==id){
                return temp;
            }
        }

        return null;
    }

    public List<Blog> searchBlogs(String words){
        List<Blog> searchedBlogs = new ArrayList<>();
        ListIterator<Blog> blogListIterator = blogs.listIterator();

        while(blogListIterator.hasNext()){
            Blog temp = blogListIterator.next();
            String title = temp.getTitle().toLowerCase();
            String content = temp.getContent().toLowerCase();

            if(title.contains(words.toLowerCase())||content.contains(words.toLowerCase())){
                searchedBlogs.add(temp);
            }
        }

        return searchedBlogs;
    }

    public  Blog craeteBlog(int id, String title, String content){
        Blog blog = new Blog(id, title, content);
        blogs.add(blog);

        return  blog;
    }

    public Blog updateBlog(int id, String title, String content){
        for(Blog blog: blogs){
            if(blog.getId()==id){
                int index = blogs.indexOf(blog);
                blog.setTitle(title);
                blog.setContent(content);
                blogs.set(index, blog);

                return blog;
            }
        }

        return null;
    }

    public boolean delete(int id){
        for(Blog blog : blogs){
            if(blog.getId()==id){
                int index = blogs.indexOf(blog);
                blogs.remove(index);

                return true;
            }
        }

        return false;
    }
}
