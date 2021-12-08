package de.gruppeo.wise2122_java_server.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class AppController {
    final
    JdbcTemplate jdbcTemplate;

    public AppController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("/")
    String index(Model model) {
        List<Map<String, Object>> player = jdbcTemplate.queryForList("select * from mibquizzz.quizPlayer");
        model.addAttribute("title", "Liste der Spieler");
        model.addAttribute("spieler", player);
        return "index";
    }
}
