/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thespheres.connect.untis.model;

import com.google.gson.annotations.JsonAdapter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author boris
 */
public class Reschedule {

    @JsonAdapter(LocalDateAdapter.class)
    private LocalDate date;
    @JsonAdapter(LocalTimeAdapter.class)
    private LocalTime time;

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

}
