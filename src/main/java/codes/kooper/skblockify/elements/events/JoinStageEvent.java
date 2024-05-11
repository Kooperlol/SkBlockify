package codes.kooper.skblockify.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import codes.kooper.blockify.events.PlayerJoinStageEvent;
import codes.kooper.blockify.models.Stage;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class JoinStageEvent {
    static {
        Skript.registerEvent(
                "Blockify Join Stage",
                SimpleEvent.class,
                PlayerJoinStageEvent.class,
                "[blockify] player join stage"
        );

        EventValues.registerEventValue(PlayerJoinStageEvent.class, Player.class, new Getter<>() {
            @Nonnull
            @Override
            public Player get(PlayerJoinStageEvent event) {
                return event.getPlayer();
            }
        }, 0);

        EventValues.registerEventValue(PlayerJoinStageEvent.class, Stage.class, new Getter<>() {
            @Nonnull
            @Override
            public Stage get(PlayerJoinStageEvent event) {
                return event.getStage();
            }
        }, 0);
    }
}
