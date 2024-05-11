package codes.kooper.skblockify.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import codes.kooper.blockify.events.BlockifyPlaceEvent;
import codes.kooper.blockify.models.Stage;
import codes.kooper.blockify.models.View;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class PlaceEvent {
    static {
        Skript.registerEvent(
                "Blockify Place",
                SimpleEvent.class,
                BlockifyPlaceEvent.class,
                "blockify place"
        );

        EventValues.registerEventValue(BlockifyPlaceEvent.class, Player.class, new Getter<>() {
            @Nonnull
            @Override
            public Player get(BlockifyPlaceEvent event) {
                return event.getPlayer();
            }
        }, 0);

        EventValues.registerEventValue(BlockifyPlaceEvent.class, Location.class, new Getter<>() {
            @Nonnull
            @Override
            public Location get(BlockifyPlaceEvent event) {
                return event.getPosition().toLocation(event.getPlayer().getWorld());
            }
        }, 0);

        EventValues.registerEventValue(BlockifyPlaceEvent.class, View.class, new Getter<>() {
            @Nonnull
            @Override
            public View get(BlockifyPlaceEvent event) {
                return event.getView();
            }
        }, 0);

        EventValues.registerEventValue(BlockifyPlaceEvent.class, Stage.class, new Getter<>() {
            @Nonnull
            @Override
            public Stage get(BlockifyPlaceEvent event) {
                return event.getStage();
            }
        }, 0);
    }
}
