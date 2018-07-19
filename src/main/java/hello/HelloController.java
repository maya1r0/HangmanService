package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;

@RestController
public class HelloController {

    @Autowired private JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String index() {
        jdbcTemplate.execute("insert into leaderboard values('name1', 0)");
        return "Greetings from Spring Boot!";
    }
    
}
