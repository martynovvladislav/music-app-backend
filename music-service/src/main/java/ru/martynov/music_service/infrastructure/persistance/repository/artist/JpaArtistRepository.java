package ru.martynov.music_service.infrastructure.persistance.repository.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.martynov.music_service.domain.entity.Artist;
import ru.martynov.music_service.domain.repository.ArtistRepository;
import ru.martynov.music_service.infrastructure.persistance.model.ArtistEntity;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaArtistRepository implements ArtistRepository {
    private final SpringDataArtistRepository springDataArtistRepository;

    @Override
    public Artist save(Artist artist) {
        final ArtistEntity artistEntity = ArtistEntity.fromDomainEntity(artist);
        return springDataArtistRepository.save(artistEntity).toDomainEntity();
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return springDataArtistRepository.findById(id).map(ArtistEntity::toDomainEntity);
    }
}
