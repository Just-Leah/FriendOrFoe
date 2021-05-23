package io.github.stonewall0210.friendorfoe.game.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;

public class FriendOrFoeMapConfig {
    public static final Codec<FriendOrFoeMapConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockState.CODEC.fieldOf("spawn_block").forGetter(map -> map.spawnBlock)
    ).apply(instance, FriendOrFoeMapConfig::new));

    public final BlockState spawnBlock;

    public FriendOrFoeMapConfig(BlockState spawnBlock) {
        this.spawnBlock = spawnBlock;
    }
}
