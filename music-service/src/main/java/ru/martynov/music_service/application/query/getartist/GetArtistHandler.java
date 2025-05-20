package ru.martynov.music_service.application.query.getartist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.martynov.music_service.application.exception.BusinessException;
import ru.martynov.music_service.application.query.QueryHandler;
import ru.martynov.music_service.application.result.Result;
import ru.martynov.music_service.domain.entity.Album;
import ru.martynov.music_service.domain.entity.Artist;
import ru.martynov.music_service.domain.repository.AlbumRepository;
import ru.martynov.music_service.domain.repository.ArtistRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetArtistHandler implements QueryHandler<GetArtistPageInfo, ArtistSummary> {
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    @Override
    public Result<ArtistSummary> handle(GetArtistPageInfo query) {
        Artist artist = artistRepository.findById(query.getArtistId()).orElseThrow(() -> new BusinessException("Artist not found"));

        List<Album> albums = albumRepository.findAlbumsByArtist(artist);

        return Result.success(mapToArtistSummary(artist, albums));
    }

    private ArtistSummary mapToArtistSummary(Artist artist, List<Album> albums) {
        List<AlbumPreview> albumSummaries = albums.stream().map(this::mapToAlbumSummary).toList();

        return ArtistSummary.builder()
                .artistId(artist.getArtistId())
                .bio(artist.getBio())
                .name(artist.getName())
                .photoUrl(artist.getPhotoUrl())
                .albums(albumSummaries)
                .build();
    }

    private AlbumPreview mapToAlbumSummary(Album album) {
        return AlbumPreview.builder()
                .id(album.getAlbumId())
                .title(album.getTitle())
                .releaseDate(album.getReleaseDate())
                .coverUrl(album.getCoverUrl())
                .artistId(album.getArtist().getArtistId())
                .artistName(album.getArtist().getName())
                .build();
    }

    @Override
    public Class<GetArtistPageInfo> getQueryType() {
        return GetArtistPageInfo.class;
    }
}

