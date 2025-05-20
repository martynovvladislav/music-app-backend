package ru.martynov.search_service.repository;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.martynov.search_service.document.Album;
import ru.martynov.search_service.document.Artist;

import java.util.List;

public interface ArtistRepository extends ElasticsearchRepository<Artist, Long> {
    @Query("{\"match_phrase_prefix\": { \"name\": \"?0\" }}")
    List<Artist> findByNamePrefix(String prefix);
}
