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
public class APIResponse extends BaseAPIResponse {

    @SerializedName("result")
    protected APIResult[] results;

    protected APIResponse() {
    }

    public APIResult[] getResults() {
        return results;
    }


}
