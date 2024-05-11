package codes.kooper.skblockify.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import codes.kooper.blockify.events.BlockifyInteractEvent;
import codes.kooper.blockify.models.Stage;
import codes.kooper.blockify.models.View;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class InteractEvent {
    static {
        Skript.registerEvent(
                "Blockify Interact",
                SimpleEvent.class,
                BlockifyInteractEvent.class,
                "blockify interact"
        );

        EventValues.registerEventValue(BlockifyInteractEvent.class, Player.class, new Getter<>() {
            @Nonnull
            @Override
            public Player get(BlockifyInteractEvent event) {
                return event.getPlayer();
            }
        }, 0);

        EventValues.registerEventValue(BlockifyInteractEvent.class, Location.class, new Getter<>() {
            @Nonnull
            @Override
            public Location get(BlockifyInteractEvent event) {
                return event.getPosition().toLocation(event.getPlayer().getWorld());
            }
        }, 0);

        EventValues.registerEventValue(BlockifyInteractEvent.class, BlockData.class, new Getter<>() {
            @Nonnull
            @Override
            public BlockData get(BlockifyInteractEvent event) {
                return event.getBlockData();
            }
        }, 0);

        EventValues.registerEventValue(BlockifyInteractEvent.class, View.class, new Getter<>() {
            @Nonnull
            @Override
            public View get(BlockifyInteractEvent event) {
                return event.getView();
            }
        }, 0);

        EventValues.registerEventValue(BlockifyInteractEvent.class, Stage.class, new Getter<>() {
            @Nonnull
            @Override
            public Stage get(BlockifyInteractEvent event) {
                return event.getStage();
            }
        }, 0);
    }

}
