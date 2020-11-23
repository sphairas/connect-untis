/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thespheres.connect.untis.model;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author boris.heithecker@gmx.net
 */
public class APIRequest {

    public enum TimetableType {
        KLASSE,
        TEACHER,
        SUBJECT,
        ROOM,
        STUDENT
    }

    public static final String JSONRCP = "2.0";
    static AtomicInteger requests = new AtomicInteger(1);
    private final String jsonrpc;
    private String id;
    private String method;
    @SerializedName("params")
    private Map<String, Object> params = new HashMap<>();

    public APIRequest(final String method) {
        this(nextRequest(), method);
        this.method = method;
    }

    public APIRequest(final String id, final String method) {
        this();
        this.id = id;
        this.method = method;
    }

    APIRequest() {
        this.jsonrpc = JSONRCP;
    }

    private static String nextRequest() {
        return "req-" + Integer.toString(requests.getAndIncrement());
    }

    public String getId() {
        return id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setStringParameter(final String key, final String value) {
        params.put(key, value);
    }

    public void setTimetableTypeParameter(final TimetableType type) {
        params.put("type", type);
    }

    public RequestOptions getRequestOptions() {
        return (RequestOptions) params.computeIfAbsent("options", k -> new RequestOptions());
    }

}
