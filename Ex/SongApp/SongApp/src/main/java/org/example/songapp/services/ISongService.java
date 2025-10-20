package org.example.songapp.services;
import org.example.songapp.models.Song;

import java.util.List;

public interface ISongService {

    List<Song> getAll();

    Song getById(int id);
    Song create(Song song);
    Song update(int id, Song song);
    void delete(int id);

}
