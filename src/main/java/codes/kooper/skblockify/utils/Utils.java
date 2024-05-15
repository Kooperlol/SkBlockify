package codes.kooper.skblockify.utils;

import codes.kooper.blockify.utils.BlockUtils;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Utils {

    public static Map<BlockData, Double> parseMaterialValues(String input) {
        Map<BlockData, Double> materialValueMap = new HashMap<>();
        String[] entries = input.split("\\|");
        for (String entry : entries) {
            String[] parts = entry.split(":");
            if (parts.length != 2) {
                continue;
            }
            String materialName = parts[0].replaceAll("\\[AGE=\\d+]", "");
            String valueStr = parts[1];
            if (Material.getMaterial(materialName) == null) {
                continue;
            }
            BlockData blockData = Objects.requireNonNull(Material.getMaterial(materialName)).createBlockData();
            if (parts[0].contains("[AGE")) {
                int age = Integer.parseInt(parts[0].substring(parts[0].indexOf("=") + 1, parts[0].indexOf("]")));
                BlockUtils.setAge(blockData, age);
            }
            Double value = Double.parseDouble(valueStr);
            materialValueMap.put(blockData, value);
        }
        return materialValueMap;
    }

}
