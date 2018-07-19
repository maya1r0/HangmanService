package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired private JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String index() {
//        jdbcTemplate.execute("insert into leaderboard values('name1', 0)");
        return "Greetings from Spring Boot!";
    }
    @RequestMapping("/get-leaderboard")
    public List<Map<String,Object>> getLeaderboard() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from  leaderboard");
//        for(Map<String, Object> m: maps){
//
//        }
        return maps;
    }
    @RequestMapping("/insert-into-leaderboard")
    public void insertIntoLeaderboard(@RequestParam(value = "name")String name,@RequestParam(value = "score") int score) {
        jdbcTemplate.execute("insert into leaderboard values('"+name+"', "+score+")");
     }
    
}
