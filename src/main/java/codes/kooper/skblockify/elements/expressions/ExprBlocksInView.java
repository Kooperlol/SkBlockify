package codes.kooper.skblockify.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.models.View;
import codes.kooper.blockify.types.BlockifyPosition;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@Name("Blocks in View")
@Description("Get all blocks in a view as block data. Call asynchronously if the view is large.")
@Examples("set {_blocks::*} to all blocks in view {_view}")
@Since("1.0.0")
public class ExprBlocksInView extends SimpleExpression<BlockData> {
    private Expression<View> view;

    static {
        Skript.registerExpression(ExprBlocksInView.class, BlockData.class, ExpressionType.SIMPLE, "[get|get all|all] blocks in view %view%");
    }

    @Override
    protected BlockData @NotNull [] get(@NotNull Event event) {
        View view = this.view.getSingle(event);
        if (view == null) return new BlockData[0];
        ArrayList<BlockData> blocks = new ArrayList<>();
        for (ConcurrentHashMap<BlockifyPosition, BlockData> blockData : view.getBlocks().values()) {
            blocks.addAll(blockData.values());
        }
        return blocks.toArray(new BlockData[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends BlockData> getReturnType() {
        return BlockData.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "Blocks in view with view expression " + view.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        view = (Expression<View>) expressions[0];
        return view != null;
    }

}
