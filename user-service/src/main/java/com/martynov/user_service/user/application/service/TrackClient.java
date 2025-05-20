package com.martynov.user_service.user.application.service;

import com.martynov.user_service.user.application.dto.music.TrackDto;

import java.util.List;

public interface TrackClient {
    List<TrackDto> getTracksByIds(List<Long> trackIds);
}
