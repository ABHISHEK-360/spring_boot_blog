package org.wells.repository;
//abhishek360

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wells.models.Blog;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByTitleContainingOrContentContaining(String text, String textAgain);
}
