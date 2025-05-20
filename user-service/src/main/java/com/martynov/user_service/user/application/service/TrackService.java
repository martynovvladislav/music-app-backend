package com.martynov.user_service.user.application.service;

import com.martynov.user_service.user.application.dto.music.TrackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TrackService {
    private final TrackClient trackClient;

    public List<TrackDto> getTracksByIds(List<Long> trackIds) {
        return trackClient.getTracksByIds(trackIds);
    }

}
