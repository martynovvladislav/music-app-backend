package ru.martynov.search_service.repository;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.martynov.search_service.document.Track;

import java.util.List;

public interface TrackRepository extends ElasticsearchRepository<Track, Long> {
    @Query("{\"match_phrase_prefix\": { \"title\": \"?0\" }}")
    List<Track> findByTitlePrefix(String prefix);
}
