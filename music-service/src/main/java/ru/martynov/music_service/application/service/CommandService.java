package ru.martynov.music_service.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.martynov.music_service.application.command.Command;
import ru.martynov.music_service.application.command.CommandHandler;
import ru.martynov.music_service.application.exception.BusinessException;
import ru.martynov.music_service.application.query.Query;
import ru.martynov.music_service.application.query.QueryHandler;
import ru.martynov.music_service.application.result.Result;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final List<CommandHandler<?>> commandHandlers;

    public <T extends Command> Result<Void> execute(T command) {
        try {
            return commandHandlers.stream()
                    .filter(commandHandler -> commandHandler.getCommandType().equals(command.getClass()))
                    .findFirst()
                    .map(commandHandler -> ((CommandHandler<T>) commandHandler).handle(command))
                    .orElse(Result.failure("No handler found for command: " + command.getClass().getSimpleName()));
        } catch (BusinessException e) {
            return Result.failure("Business error: " + e.getMessage());
        } catch (Exception e) {
            return Result.failure("Unexpected error: " + e.getMessage());
        }
    }
}
