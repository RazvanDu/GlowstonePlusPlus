package net.glowstone.net.message.play.entity;

import com.flowpowered.networking.Message;
import lombok.Data;
import net.glowstone.entity.AttributeManager;

import java.util.Map;

@Data
public final class EntityPropertyMessage implements Message {
    public final int id;
    public final Map<String, AttributeManager.Property> properties;
}
