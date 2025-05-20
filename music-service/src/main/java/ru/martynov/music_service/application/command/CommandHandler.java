package ru.martynov.music_service.application.command;

import ru.martynov.music_service.application.result.Result;


public interface CommandHandler<T extends Command> {
    Result<Void> handle(T command);
    Class<T> getCommandType();
}