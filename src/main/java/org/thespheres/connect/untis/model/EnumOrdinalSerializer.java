/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thespheres.connect.untis.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author boris.heithecker@gmx.net
 * @param <E>
 */
public class EnumOrdinalSerializer<E extends Enum> implements JsonSerializer<E>, JsonDeserializer<E> {

    @Override
    public JsonElement serialize(final E item, final Type type, JsonSerializationContext ctx) {
        return item == null ? JsonNull.INSTANCE : new JsonPrimitive(item.ordinal() + 1);
    }

    @Override
    public E deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext ctx) throws JsonParseException {
        try {
            final Class<?> clz = Class.forName(type.getTypeName());
            final Object[] constants = clz.getEnumConstants();
            final int ordinal = jsonElement.getAsInt();
            return (E) ((constants.length >= 1 && ordinal >= 1) ? constants[ordinal - 1] : null);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); // fancy error handling here
        }
    }

}
