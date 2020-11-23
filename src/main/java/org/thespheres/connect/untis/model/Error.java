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
public class Error {

    private String message;
    private int code;

    Error() {
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Error{" + "message=" + message + ", code=" + code + '}';
    }

}
