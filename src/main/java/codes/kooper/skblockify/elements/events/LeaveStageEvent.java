package codes.kooper.skblockify.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import codes.kooper.blockify.events.PlayerLeaveStageEvent;
import codes.kooper.blockify.models.Stage;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class LeaveStageEvent {
    static {
        Skript.registerEvent(
                "Blockify Leave Stage",
                SimpleEvent.class,
                PlayerLeaveStageEvent.class,
                "[blockify] player leave stage"
        );

        EventValues.registerEventValue(PlayerLeaveStageEvent.class, Player.class, new Getter<>() {
            @Nonnull
            @Override
            public Player get(PlayerLeaveStageEvent event) {
                return event.getPlayer();
            }
        }, 0);

        EventValues.registerEventValue(PlayerLeaveStageEvent.class, Stage.class, new Getter<>() {
            @Nonnull
            @Override
            public Stage get(PlayerLeaveStageEvent event) {
                return event.getStage();
            }
        }, 0);
    }
}
