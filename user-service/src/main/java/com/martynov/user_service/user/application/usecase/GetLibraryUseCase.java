package com.martynov.user_service.user.application.usecase;

import com.martynov.user_service.common.application.utils.Result;
import com.martynov.user_service.user.application.dto.music.LibraryDto;
import com.martynov.user_service.user.application.dto.music.LikeDto;
import com.martynov.user_service.user.application.dto.music.TrackDto;
import com.martynov.user_service.user.application.dto.music.UserLibraryRequest;
import com.martynov.user_service.user.application.service.LikeService;
import com.martynov.user_service.user.application.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetLibraryUseCase {
    private final LikeService likeService;
    private final TrackService trackService;

    public Result<LibraryDto> execute(final UserLibraryRequest userLibraryRequest) {
        final List<LikeDto> likedTracks = likeService.getLikesByUserId(userLibraryRequest.userId);
        final List<TrackDto> trackDtos = trackService.getTracksByIds(
                likedTracks.stream().map(likeDto -> likeDto.trackId).toList()
        );

        final List<TrackDto> sortedTracks = sortTracksByLikedAt(likedTracks, trackDtos);

        return Result.success(new LibraryDto(sortedTracks));
    }


    private List<TrackDto> sortTracksByLikedAt(final List<LikeDto> likeDtos, final List<TrackDto> trackDtos) {
        Map<Long, LocalDateTime> likedAtMap = likeDtos.stream()
                .collect(Collectors.toMap(like -> like.trackId, like -> like.likedAt));

        return trackDtos.stream()
                .sorted(Comparator.comparing(
                        track -> likedAtMap.getOrDefault(track.trackId, LocalDateTime.MIN),
                        Comparator.reverseOrder()
                ))
                .toList();
    }
}
