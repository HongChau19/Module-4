package org.example.songapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Tên bài hát không được để trống")
    @Size(max=200)
    private String title;

    @NotBlank(message= "Tên nghệ sĩ không được để trống")
    @Size(max=150)
    private String artist;

    @NotBlank(message= "Thể loại không được để trống")
    @Size(max=100)
    private String genre;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    public Song(){
    }

    public Song(String name, String artist, String genre, String filePath) {
        this.title = name;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String name) {
        this.title = name;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
