package codes.kooper.skblockify.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.models.Stage;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Set Chunk Ticks")
@Description("Sets the amount of chunks sent per tick to the audience.")
@Examples("set chunks sent per tick to 10 for stage {_stage}")
@Since("1.0.3")
public class EffChunkTicks extends Effect {
    private Expression<Number> chunks;
    private Expression<Stage> stage;

    static {
        Skript.registerEffect(EffChunkTicks.class, "set chunks sent per tick to %number% for stage %stage%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        Number chunks = this.chunks.getSingle(event);
        Stage stage = this.stage.getSingle(event);
        if (chunks == null || stage == null) {
            return;
        }
        stage.setChunksPerTick(chunks.intValue());
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Set chunks per tick with expression chunks: " + chunks.toString(event, debug) + " and stage: " + stage.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        chunks = (Expression<Number>) expressions[0];
        stage = (Expression<Stage>) expressions[1];
        return (chunks != null && stage != null);
    }
}
