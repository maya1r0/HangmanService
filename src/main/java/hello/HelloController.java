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
    @RequestMapping(value = "/store-username")
    public void storeUsername(@RequestParam(value = "name")String name) {
        jdbcTemplate.execute("insert into player_info values('"+name+"', 0, 0)");
    }

    @CrossOrigin
    @RequestMapping("/up-streaks")
    public void upStreaks(@RequestParam(value = "name") String playerName) {
        int currStreak = jdbcTemplate.queryForInt("select current_streak from player_info WHERE name = '" + playerName+"'") + 1;
        int maxStreak = jdbcTemplate.queryForInt("select max_streak from player_info WHERE name = '" + playerName + "'") ;
        if (currStreak >= maxStreak) {
            jdbcTemplate.execute("UPDATE player_info SET current_streak =" + currStreak + ", max_streak = " + currStreak + "WHERE name = '" + playerName + "'");
        }
        else{
            jdbcTemplate.execute("UPDATE player_info SET current_streak =" + currStreak + " WHERE name = '" + playerName + "'");
        }
    }

    @CrossOrigin
    @RequestMapping("/reset-current-streak")
    public void resetCurrStreak(@RequestParam(value = "name") String playerName) {
        jdbcTemplate.execute("UPDATE player_info SET current_streak = 0 WHERE name = '" + playerName + "'");
    }


    @CrossOrigin
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String hello() {
        return "Hello world!";
    }
    
}
