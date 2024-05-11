package codes.kooper.skblockify.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import codes.kooper.blockify.events.OnBlockChangeSendEvent;
import codes.kooper.blockify.models.Stage;
import org.bukkit.Location;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class BlockChangeSendEvent {
    static {
        Skript.registerEvent(
                "Blockify Block Change Send",
                SimpleEvent.class,
                OnBlockChangeSendEvent.class,
                "[blockify] block change send"
        );

        EventValues.registerEventValue(OnBlockChangeSendEvent.class, Stage.class, new Getter<>() {
            @Nonnull
            @Override
            public Stage get(OnBlockChangeSendEvent event) {
                return event.getStage();
            }
        }, 0);

        EventValues.registerEventValue(OnBlockChangeSendEvent.class, List.class, new Getter<>() {
            @Nonnull
            @Override
            public List<Location> get(OnBlockChangeSendEvent event) {
                List<Location> locations = new ArrayList<>();
                for (var chunk : event.getBlocks().keySet()) {
                    for (var position : event.getBlocks().get(chunk).keySet()) {
                        locations.add(position.toLocation(event.getStage().getWorld()));
                    }
                }
                return locations;
            }
        }, 0);
    }
}
