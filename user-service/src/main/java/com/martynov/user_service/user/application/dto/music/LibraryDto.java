package com.martynov.user_service.user.application.dto.music;

import com.martynov.user_service.user.application.dto.music.TrackDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LibraryDto {
    public List<TrackDto> tracks;
}
