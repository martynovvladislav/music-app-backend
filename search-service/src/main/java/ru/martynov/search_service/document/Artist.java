package ru.martynov.search_service.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "artist")
@Builder
public class Artist {
    @Id
    private Long artistId;
    private String name;
    private String photoUrl;
}
