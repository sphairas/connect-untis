/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thespheres.connect.untis.model;

/**
 *
 * @author boris.heithecker@gmx.net
 */
public abstract class BaseAPIResponse {
    
    protected String jsonrpc;
    protected String id;
    protected Error error;

    public String getId() {
        return id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public Error getError() {
        return error;
    }
    
}
