package org.example.songapp.controllers;

import org.example.songapp.models.Song;
import org.example.songapp.services.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    private ISongService songService;

    @GetMapping
    public String list(Model model, @ModelAttribute("Message") String message) {
        model.addAttribute("songs", songService.getAll());
        return "song/list";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("song", new Song());
        return "song/form";
    }

    @PostMapping("new")
    public String doCreate(@ModelAttribute("song") Song song) {
        return "redirect:/song";
    }
}
