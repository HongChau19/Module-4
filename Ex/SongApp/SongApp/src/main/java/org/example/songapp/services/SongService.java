package org.example.songapp.services;

import jakarta.persistence.EntityManager;
import org.example.songapp.models.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // để Spring tạo Bean (object) trong IoC Container
public class SongService implements ISongService {
    private static EntityManager entityManager;
    private static SessionFactory sessionFactory;

    //khởi tạo 1 lần duy nhất SongService
    static {
        sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
        entityManager = sessionFactory.createEntityManager();
    }

    @Override
    public List<Song> getAll () {
        String query = "SELECT s FROM Song s ORDER BY s.id ASC";
        return entityManager.createQuery(query).getResultList();
    }
    @Override
    public Song getById(int id) {
        String query = "SELECT s FROM Song s WHERE s.id = :id";
        return entityManager.createQuery(query, Song.class).setParameter("id", id).getSingleResult();

    }
    @Override
    public Song create(Song song) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Song savedSong = new Song();
        savedSong.setTitle(song.getTitle());
        savedSong.setArtist(song.getArtist());
        savedSong.setGenre(song.getGenre());
        savedSong.setFilePath(song.getFilePath());
        session.persist(savedSong);
        tx.commit();
        session.close();
        return savedSong;
    }

    @Override
    public Song update(int id, Song song) {
        Song savedSong = getById(id);
        savedSong.setTitle(song.getTitle());
        savedSong.setArtist(song.getArtist());
        savedSong.setGenre(song.getGenre());
        savedSong.setFilePath(song.getFilePath());
        savedSong = entityManager.merge(savedSong);
        return savedSong;
    }

    @Override
    public void delete(int id) {
        Song savedSong = getById(id);
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.remove( savedSong);
        tx.commit();
        session.close();
    }
}
