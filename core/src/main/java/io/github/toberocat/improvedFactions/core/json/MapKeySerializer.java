package io.github.toberocat.improvedFactions.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.toberocat.improvedFactions.core.item.ItemStack;

import java.io.IOException;

public class MapKeySerializer extends JsonSerializer<ItemStack> {

    @Override
    public void serialize(ItemStack value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException { // ToDo Fix serializer
/*        if (value.stack() == null) {
            gen.writeFieldName(value.slot() + " null");
        } else {
            gen.writeFieldName(value.slot() + " " + value.stack().toBase64());
        }*/
    }
}
