/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thespheres.connect.untis.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author boris.heithecker@gmx.net
 */
public class RequestElement {

    public enum KeyType {

        @SerializedName("id")
        ID,
        @SerializedName("name")
        NAME,
        @SerializedName("externalkey")
        EXTERNAL_KEY

    }

    private final String id;
    private final APIRequest.TimetableType type;
    private final KeyType keyType;

    public RequestElement(final String id, final APIRequest.TimetableType type) {
        this(id, type, null);
    }

    public RequestElement(final String id, final APIRequest.TimetableType type, final KeyType keyType) {
        this.id = id;
        this.type = type;
        this.keyType = keyType;
    }

    public String getId() {
        return id;
    }

    public APIRequest.TimetableType getType() {
        return type;
    }

    public KeyType getKeyType() {
        return keyType;
    }

}
