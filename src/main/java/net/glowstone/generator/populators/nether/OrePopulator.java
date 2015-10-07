package net.glowstone.generator.populators.nether;

import net.glowstone.generator.objects.OreType;
import net.glowstone.generator.objects.OreVein;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Populates the world with ores.
 */
public class OrePopulator extends BlockPopulator {

    private final Map<OreType, Integer> ores = new LinkedHashMap<>();

    public OrePopulator() {
        ores.put(new OreType(Material.QUARTZ_ORE, 10, 118, 13, Material.NETHERRACK), 16);
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        final int cx = (chunk.getX() << 4);
        final int cz = (chunk.getZ() << 4);

        for (Entry<OreType, Integer> entry : ores.entrySet()) {

            final OreType oreType = entry.getKey();
            for (int n = 0; n < entry.getValue(); n++) {

                int sourceX = cx + random.nextInt(16);
                int sourceZ = cz + random.nextInt(16);
                int sourceY = oreType.getMinY() == oreType.getMaxY() ?
                        random.nextInt(oreType.getMinY()) + random.nextInt(oreType.getMinY()) :
                            random.nextInt(oreType.getMaxY() - oreType.getMinY()) + oreType.getMinY();

                new OreVein(oreType).generate(world, random, sourceX, sourceY, sourceZ);
            }
        }
    }
}