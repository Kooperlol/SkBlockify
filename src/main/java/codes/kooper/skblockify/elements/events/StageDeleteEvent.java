package codes.kooper.skblockify.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import codes.kooper.blockify.events.DeleteStageEvent;
import codes.kooper.blockify.models.Stage;

import javax.annotation.Nonnull;

public class StageDeleteEvent {
    static {
        Skript.registerEvent(
                "Blockify Delete Stage",
                SimpleEvent.class,
                DeleteStageEvent.class,
                "[blockify] stage delete[d]"
        );

        EventValues.registerEventValue(DeleteStageEvent.class, Stage.class, new Getter<>() {
            @Nonnull
            @Override
            public Stage get(DeleteStageEvent event) {
                return event.getStage();
            }
        }, 0);
    }
}
