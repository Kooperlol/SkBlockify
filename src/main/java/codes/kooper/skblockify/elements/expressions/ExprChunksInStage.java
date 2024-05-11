package codes.kooper.skblockify.elements.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.models.Stage;
import codes.kooper.blockify.types.BlockifyChunk;
import org.bukkit.Chunk;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Chunks in Stage")
@Description("Get all chunks in a stage.")
@Examples("set {_chunks::*} to all chunks in stage {_stage}")
@Since("1.0.0")
public class ExprChunksInStage extends SimpleExpression<Chunk> {
    private Expression<Stage> stage;

    @Override
    protected Chunk @NotNull [] get(@NotNull Event event) {
        Chunk[] chunks = new Chunk[stage.getAll(event).length];
        Stage stage = this.stage.getSingle(event);
        if (stage == null) {
            return new Chunk[0];
        }
        int i = 0;
        for (BlockifyChunk blockifyChunk : stage.getChunks()) {
            chunks[i] = stage.getWorld().getChunkAt(blockifyChunk.x(), blockifyChunk.z());
            i++;
        }
        return chunks;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Chunk> getReturnType() {
        return Chunk.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "Chunks in stage with stage expression " + stage.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (Expression<Stage>) expressions[0];
        return stage != null;
    }

}
