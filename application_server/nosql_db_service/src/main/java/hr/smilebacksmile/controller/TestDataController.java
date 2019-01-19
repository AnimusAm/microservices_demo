package hr.smilebacksmile.controller;

import hr.smilebacksmile.dao.TestDataRepository;
import hr.smilebacksmile.domain.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestDataController {


    TestDataRepository dataRepository;

    @Autowired
    public TestDataController(TestDataRepository dataRepository) {
        this.dataRepository = dataRepository;
        this.dataRepository.save(new TestData("Alice", "Smith"));
        this.dataRepository.save(new TestData("Bob", "Smith"));
    }

    @GetMapping("/data")
    public List<TestData> index(){
        return dataRepository.findAll();
    }

    @GetMapping("/data/{code}")
    public TestData show(@PathVariable String code){
        return dataRepository.findByCode(code);
    }

    /*
    @PostMapping("/blog/search")
    public List<Blog> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return blogRespository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/blog")
    public Blog create(@RequestBody Map<String, String> body){
        String title = body.get("title");
        String content = body.get("content");
        return blogRespository.save(new Blog(title, content));
    }

    @PutMapping("/blog/{id}")
    public Blog update(@PathVariable String id, @RequestBody Map<String, String> body){
        int blogId = Integer.parseInt(id);
        // getting blog
        Blog blog = blogRespository.findOne(blogId);
        blog.setTitle(body.get("title"));
        blog.setContent(body.get("content"));
        return blogRespository.save(blog);
    }

    @DeleteMapping("blog/{id}")
    public boolean delete(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        blogRespository.delete(blogId);
        return true;
    }
    */
}
