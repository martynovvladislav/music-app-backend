package ru.martynov.search_service.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "album")
@Builder
public class Album {
    @Id
    private Long albumId;
    private String title;
    private Long artistId;
    private String artistName;
    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDate releaseDate;
    private String coverUrl;
}
