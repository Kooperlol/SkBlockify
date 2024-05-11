package codes.kooper.skblockify.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import codes.kooper.blockify.events.BlockifyBreakEvent;
import codes.kooper.blockify.models.Stage;
import codes.kooper.blockify.models.View;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class BreakEvent {
    static {
        Skript.registerEvent(
            "Blockify Break",
            SimpleEvent.class,
            BlockifyBreakEvent.class,
            "blockify (break|mine|destroy)"
        );

        EventValues.registerEventValue(BlockifyBreakEvent.class, Player.class, new Getter<>() {
            @Nonnull
            @Override
            public Player get(BlockifyBreakEvent event) {
                return event.getPlayer();
            }
        }, 0);

        EventValues.registerEventValue(BlockifyBreakEvent.class, Location.class, new Getter<>() {
            @Nonnull
            @Override
            public Location get(BlockifyBreakEvent event) {
                return event.getPosition().toLocation(event.getPlayer().getWorld());
            }
        }, 0);

        EventValues.registerEventValue(BlockifyBreakEvent.class, BlockData.class, new Getter<>() {
            @Nonnull
            @Override
            public BlockData get(BlockifyBreakEvent event) {
                return event.getBlockData();
            }
        }, 0);

        EventValues.registerEventValue(BlockifyBreakEvent.class, View.class, new Getter<>() {
            @Nonnull
            @Override
            public View get(BlockifyBreakEvent event) {
                return event.getView();
            }
        }, 0);

        EventValues.registerEventValue(BlockifyBreakEvent.class, Stage.class, new Getter<>() {
            @Nonnull
            @Override
            public Stage get(BlockifyBreakEvent event) {
                return event.getStage();
            }
        }, 0);
    }
}