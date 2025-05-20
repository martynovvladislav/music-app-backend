package ru.martynov.search_service.repository;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.martynov.search_service.document.Album;
import ru.martynov.search_service.document.Track;

import java.util.List;

public interface AlbumRepository extends ElasticsearchRepository<Album, Long> {
    @Query("{\"match_phrase_prefix\": { \"title\": \"?0\" }}")
    List<Album> findByTitlePrefix(String prefix);
}
