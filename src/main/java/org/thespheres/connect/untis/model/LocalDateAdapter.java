/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thespheres.connect.untis.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author boris.heithecker@gmx.net
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public void write(final JsonWriter writer, final LocalDate value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        final String v = DTF.format(value);
        writer.value(v);
    }

    @Override
    public LocalDate read(final JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        final String v = reader.nextString();
        return LocalDate.parse(v, DTF);
    }
}
