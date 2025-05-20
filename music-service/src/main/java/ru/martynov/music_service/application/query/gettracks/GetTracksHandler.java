package ru.martynov.music_service.application.query.gettracks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.martynov.music_service.application.query.QueryHandler;
import ru.martynov.music_service.application.query.getalbum.TrackSummary;
import ru.martynov.music_service.application.result.Result;
import ru.martynov.music_service.domain.entity.Track;
import ru.martynov.music_service.domain.repository.TrackRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetTracksHandler implements QueryHandler<GetTracks, TracksInfo> {
    private final TrackRepository trackRepository;

    @Override
    public Result<TracksInfo> handle(GetTracks query) {
        final List<Track> tracks = trackRepository.findAllByIds(query.getTrackIds());

        final List<TrackSummary> trackSummaries = tracks.stream()
                .map(this::mapToTrackSummary)
                .toList();

        return Result.success(new TracksInfo(trackSummaries));
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
    public Class<GetTracks> getQueryType() {
        return GetTracks.class;
    }
}
