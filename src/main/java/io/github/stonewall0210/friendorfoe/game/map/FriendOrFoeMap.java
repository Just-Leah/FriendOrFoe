package io.github.stonewall0210.friendorfoe.game.map;

import net.minecraft.server.MinecraftServer;
import xyz.nucleoid.plasmid.map.template.MapTemplate;
import xyz.nucleoid.plasmid.map.template.TemplateChunkGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class FriendOrFoeMap {
    private final MapTemplate template;
    private final FriendOrFoeMapConfig config;
    public BlockPos spawn;

    public FriendOrFoeMap(MapTemplate template, FriendOrFoeMapConfig config) {
        this.template = template;
        this.config = config;
    }

    public ChunkGenerator asGenerator(MinecraftServer server) {
        return new TemplateChunkGenerator(server, this.template);
    }
}
