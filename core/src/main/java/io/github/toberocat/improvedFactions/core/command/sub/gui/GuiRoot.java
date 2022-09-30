/**
 * Created: 30/09/2022
 *
 * @author Tobias Madlberger (Tobias)
 */

package io.github.toberocat.improvedFactions.core.command.sub.gui;

import io.github.toberocat.improvedFactions.core.command.component.Command;
import io.github.toberocat.improvedFactions.core.command.component.CommandSettings;
import io.github.toberocat.improvedFactions.core.player.FactionPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class GuiRoot extends Command<Command.CommandPacket, Command.ConsoleCommandPacket> {

    public GuiRoot() {
        add(new ItemTranslatableCommand());
        add(new EditGuisCommand());
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

    @Override
    public @NotNull String label() {
        return "gui";
    }

    @Override
    protected @NotNull CommandSettings createSettings() {
        return new CommandSettings(node)
                .setRequiredSpigotPermission(permission());
    }

    @Override
    public @NotNull List<String> tabCompleteConsole(@NotNull String[] args) {
        return Collections.emptyList();
    }

    @Override
    public @NotNull List<String> tabCompletePlayer(@NotNull FactionPlayer<?> player, @NotNull String[] args) {
        return Collections.emptyList();
    }

    @Override
    public void run(@NotNull CommandPacket packet) {

    }

    @Override
    public void runConsole(@NotNull ConsoleCommandPacket packet) {

    }

    @Override
    public @Nullable Command.CommandPacket createFromArgs(@NotNull FactionPlayer<?> executor, @NotNull String[] args) {
        return null;
    }

    @Override
    public @Nullable Command.ConsoleCommandPacket createFromArgs(@NotNull String[] args) {
        return null;
    }
}
