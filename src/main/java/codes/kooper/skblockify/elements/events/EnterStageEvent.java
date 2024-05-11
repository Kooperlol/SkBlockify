package codes.kooper.skblockify.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import codes.kooper.blockify.events.PlayerEnterStageEvent;
import codes.kooper.blockify.models.Stage;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class EnterStageEvent {
    static {
        Skript.registerEvent(
                "Blockify Enter Stage",
                SimpleEvent.class,
                PlayerEnterStageEvent.class,
                "[blockify] player enter stage"
        );

        EventValues.registerEventValue(PlayerEnterStageEvent.class, Player.class, new Getter<>() {
            @Nonnull
            @Override
            public Player get(PlayerEnterStageEvent event) {
                return event.getPlayer();
            }
        }, 0);

        EventValues.registerEventValue(PlayerEnterStageEvent.class, Stage.class, new Getter<>() {
            @Nonnull
            @Override
            public Stage get(PlayerEnterStageEvent event) {
                return event.getStage();
            }
        }, 0);
    }
}
