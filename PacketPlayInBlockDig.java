package net.minecraft.server;

import java.io.IOException;

/**
 * Represents a packet for block digging actions.
 */
public class PacketPlayInBlockDig implements Packet<PacketListenerPlayIn> {
    private BlockPosition blockPosition;
    private EnumDirection direction;
    private EnumPlayerDigType digType;
  
    // Timestamp for the "itemDrop" packet
    public final long timestamp = System.currentTimeMillis();

    public PacketPlayInBlockDig() {
    }

    @Override
    public void deserialize(PacketDataSerializer serializer) throws IOException {
        this.digType = serializer.readEnum(EnumPlayerDigType.class);
        this.blockPosition = serializer.readBlockPosition();
        this.direction = EnumDirection.fromType1(serializer.readUnsignedByte());
    }

    @Override
    public void serialize(PacketDataSerializer serializer) throws IOException {
        serializer.writeEnum(this.digType);
        serializer.writeBlockPosition(this.blockPosition);
        serializer.writeByte(this.direction.getIndex());
    }

    @Override
    public void process(PacketListenerPlayIn listener) {
        listener.handleBlockDig(this);
    }

    public BlockPosition getBlockPosition() {
        return this.blockPosition;
    }

    public EnumDirection getDirection() {
        return this.direction;
    }

    public EnumPlayerDigType getDigType() {
        return this.digType;
    }

    public enum EnumPlayerDigType {
        START_DESTROY_BLOCK,
        ABORT_DESTROY_BLOCK,
        STOP_DESTROY_BLOCK,
        DROP_ALL_ITEMS,
        DROP_ITEM,
        RELEASE_USE_ITEM
    }
}
