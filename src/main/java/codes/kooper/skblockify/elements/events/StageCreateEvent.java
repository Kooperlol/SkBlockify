package codes.kooper.skblockify.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import codes.kooper.blockify.events.CreateStageEvent;
import codes.kooper.blockify.models.Stage;

import javax.annotation.Nonnull;

public class StageCreateEvent {
    static {
        Skript.registerEvent(
                "Blockify Create Stage",
                SimpleEvent.class,
                CreateStageEvent.class,
                "[blockify] stage create[d]"
        );

        EventValues.registerEventValue(CreateStageEvent.class, Stage.class, new Getter<>() {
            @Nonnull
            @Override
            public Stage get(CreateStageEvent event) {
                return event.getStage();
            }
        }, 0);
    }
}
