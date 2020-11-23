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
public class LastImportTimeAPIResponse extends BaseAPIResponse {

    private long result;

    public long getTimestamp() {
        return result;
    }

}
