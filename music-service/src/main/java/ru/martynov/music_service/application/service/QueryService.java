package ru.martynov.music_service.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.martynov.music_service.application.query.Query;
import ru.martynov.music_service.application.query.QueryHandler;
import ru.martynov.music_service.application.result.Result;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryService {
    private final List<QueryHandler<?, ?>> queryHandlers;

    public <R, T extends Query> Result<R> execute(T query) {
        try {
            return queryHandlers.stream()
                    .filter(queryHandler -> queryHandler.getQueryType().equals(query.getClass()))
                    .findFirst()
                    .map(queryHandler -> ((QueryHandler<T, R>) queryHandler).handle(query))
                    .orElse(Result.failure("No handler found for query: " + query.getClass().getSimpleName()));
        } catch (Exception e) {
            return Result.failure("Unexpected error: " + e.getMessage());
        }

    }
}

