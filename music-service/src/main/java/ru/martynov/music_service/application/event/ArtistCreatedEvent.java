package ru.martynov.music_service.application.event;

import lombok.Getter;

@Getter
public class ArtistCreatedEvent extends Event {
    private final Long artistId;
    private final String name;
    private final String photoUrl;

    public ArtistCreatedEvent(Object source, Long artistId, String name, String photoUrl) {
        super(source);
        this.artistId = artistId;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    @Override
    public String getType() {
        return "ARTIST_CREATED";
    }
}
