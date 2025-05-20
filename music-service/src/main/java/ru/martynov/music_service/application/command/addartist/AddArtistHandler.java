package ru.martynov.music_service.application.command.addartist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.martynov.music_service.application.command.CommandHandler;
import ru.martynov.music_service.application.event.MusicEventPublisher;
import ru.martynov.music_service.application.result.Result;
import ru.martynov.music_service.domain.entity.Artist;
import ru.martynov.music_service.domain.repository.ArtistRepository;

@Component
@RequiredArgsConstructor
public class AddArtistHandler implements CommandHandler<AddArtist> {
    private final ArtistRepository artistRepository;
    private final MusicEventPublisher musicEventPublisher;

    @Override
    @Transactional
    public Result<Void> handle(AddArtist command) {
        Artist artist = Artist.create(command.getName(), command.getBio(), command.getPhotoUrl());
        artist = artistRepository.save(artist);

        musicEventPublisher.publishArtistCreationEvent(artist);

        return Result.success();
    }

    @Override
    public Class<AddArtist> getCommandType() {
        return AddArtist.class;
    }
}
