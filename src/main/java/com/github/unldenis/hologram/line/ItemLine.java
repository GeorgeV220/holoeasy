package com.github.unldenis.hologram.line;

import com.github.unldenis.hologram.packet.PacketContainerSendable;
import com.github.unldenis.hologram.packet.PacketsFactory;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class ItemLine implements ILine<ItemStack> {

  private final Line line;
  private final PacketContainerSendable entityMetadataPacket;

  private ItemStack obj;

  public ItemLine(Line line, ItemStack obj) {
    this.line = line;
    this.entityMetadataPacket = PacketsFactory.get().metadataPacket(line.getEntityID());

    this.obj = obj;
  }

  @Override
  public Type getType() {
    return EType.ITEM_LINE;
  }

  @Override
  public int getEntityId() {
    return line.getEntityID();
  }

  @Override
  public Location getLocation() {
    return line.getLocation();
  }

  @Override
  public void setLocation(Location location) {
    line.setLocation(location);
  }

  @Override
  public ItemStack getObj() {
    return obj.clone();
  }

  @Override
  public void setObj(ItemStack obj) {
    this.obj = obj;
  }

  @Override
  public void hide(Player player) {
    line.destroy(player);
  }

  @Override
  public void teleport(Player player) {
    line.teleport(player);
  }

  @Override
  public void show(Player player) {
    line.spawn(player);
    entityMetadataPacket.send(player);
    this.update(player);
  }

  @Override
  public void update(Player player) {
    PacketsFactory.get()
        .equipmentPacket(line.getEntityID(), this.obj)
        .send(player);
  }

}
