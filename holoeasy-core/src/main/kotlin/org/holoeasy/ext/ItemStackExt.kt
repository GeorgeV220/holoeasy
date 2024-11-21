package org.holoeasy.ext

import com.comphenix.protocol.wrappers.BukkitConverters
import org.bukkit.inventory.ItemStack


internal fun ItemStack.bukkitGeneric(): Any {
    return BukkitConverters.getItemStackConverter().getGeneric(this)
}