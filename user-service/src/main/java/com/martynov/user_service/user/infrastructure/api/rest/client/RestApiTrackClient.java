package com.martynov.user_service.user.infrastructure.api.rest.client;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.martynov.user_service.user.application.dto.music.TrackDto;
import com.martynov.user_service.user.application.service.TrackClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestApiTrackClient implements TrackClient {
    private final RestClient trackRestClient;

    @Override
    public List<TrackDto> getTracksByIds(List<Long> trackIds) {

        return trackRestClient.post()
                .uri("/tracks/enrich")
                .body(new GetTracksRequest(trackIds))
                .retrieve()
                .body(GetTracksResponse.class)
                .tracks;
    }

    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static final class GetTracksRequest {
        public List<Long> trackIds;
    }

    @AllArgsConstructor
    private static final class GetTracksResponse {
        public List<TrackDto> tracks;
    }
}
