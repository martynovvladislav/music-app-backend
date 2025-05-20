package ru.martynov.music_service.application.query.getalbum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.martynov.music_service.application.exception.BusinessException;
import ru.martynov.music_service.application.query.QueryHandler;
import ru.martynov.music_service.application.result.Result;
import ru.martynov.music_service.domain.entity.Album;
import ru.martynov.music_service.domain.entity.Track;
import ru.martynov.music_service.domain.repository.AlbumRepository;
import ru.martynov.music_service.domain.repository.TrackRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAlbumHandler implements QueryHandler<GetAlbumPageInfo, AlbumSummary> {
    private final AlbumRepository albumRepository;
    private final TrackRepository trackRepository;

    @Override
    public Result<AlbumSummary> handle(GetAlbumPageInfo query) {
        final Album album = albumRepository.findById(query.getAlbumId()).orElseThrow(() -> new BusinessException("Album not found"));

        final List<Track> tracks = trackRepository.findAllByAlbum(album);

        return Result.success(mapToAlbumPageSummary(album, tracks));
    }

    private AlbumSummary mapToAlbumPageSummary(Album album, List<Track> tracks) {
        List<TrackSummary> trackSummaries = tracks.stream().map(this::mapToTrackSummary).toList();
        return AlbumSummary.builder()
                .id(album.getAlbumId())
                .title(album.getTitle())
                .artistId(album.getArtist().getArtistId())
                .artistName(album.getArtist().getName())
                .releaseDate(album.getReleaseDate())
                .coverUrl(album.getCoverUrl())
                .tracks(trackSummaries)
                .build();
    }

    private TrackSummary mapToTrackSummary(Track track) {
        return TrackSummary.builder()
                .albumId(track.getAlbum().getAlbumId())
                .albumTitle(track.getAlbum().getTitle())
                .trackId(track.getTrackId())
                .title(track.getTitle())
                .genre(track.getGenre())
                .artistId(track.getArtist().getArtistId())
                .artistName(track.getArtist().getName())
                .audioUrl(track.getAudioUrl())
                .releaseDate(track.getReleaseDate())
                .coverUrl(track.getCoverUrl())
                .build();
    }

    @Override
    public Class<GetAlbumPageInfo> getQueryType() {
        return GetAlbumPageInfo.class;
    }
}
