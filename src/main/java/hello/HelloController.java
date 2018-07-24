package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired private JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    @RequestMapping("/get-leaderboard")
    public List<Map<String,Object>> getLeaderboard() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from  leaderboard");

        return maps;
    }
    @RequestMapping("/insert-into-leaderboard")
    public void insertIntoLeaderboard(@RequestParam(value = "name")String name,@RequestParam(value = "score") int score) {
        jdbcTemplate.execute("insert into leaderboard values('"+name+"', "+score+")");
     }

    @CrossOrigin
    @RequestMapping(value = "/store-username", method = RequestMethod.POST)
    public void storeUsername(@RequestParam(value = "name")String name) {
        jdbcTemplate.execute("insert into player_info values('"+name+"', 0, 0)");
    }

    @CrossOrigin
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String hello() {
        return "Hello world!";
    }
    
}
