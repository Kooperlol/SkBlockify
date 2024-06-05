package codes.kooper.skblockify.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import codes.kooper.blockify.events.PlayerExitStageEvent;
import codes.kooper.blockify.models.Stage;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class ExitStageEvent {
    static {
        Skript.registerEvent(
                "Blockify Exit Stage",
                SimpleEvent.class,
                PlayerExitStageEvent.class,
                "[blockify] player exit stage"
        );

        EventValues.registerEventValue(PlayerExitStageEvent.class, Player.class, new Getter<>() {
            @Nonnull
            @Override
            public Player get(PlayerExitStageEvent event) {
                return event.getPlayer();
            }
        }, 0);

        EventValues.registerEventValue(PlayerExitStageEvent.class, Stage.class, new Getter<>() {
            @Nonnull
            @Override
            public Stage get(PlayerExitStageEvent event) {
                return event.getStage();
            }
        }, 0);
    }
}
