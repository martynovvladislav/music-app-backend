package ru.martynov.music_service.application.query;

import ru.martynov.music_service.application.result.Result;

public interface QueryHandler<T extends Query, R> {
    Result<R> handle(T query);
    Class<T> getQueryType();
}
