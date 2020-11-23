/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thespheres.connect.untis.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;

/**
 *
 * @author boris.heithecker@gmx.net
 */
public class RequestOptions {

    public enum RequestField {
        @SerializedName("id")
        ID,
        @SerializedName("name")
        NAME,
        @SerializedName("longname")
        LONG_NAME,
        @SerializedName("externalkey")
        EXTERNAL_KEY
    }

    private RequestElement element;
    @JsonAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @JsonAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private RequestField[] klasseFields;
    private RequestField[] subjectFields;
    private RequestField[] teacherFields;
    private RequestField[] roomFields;
    private boolean onlyBaseTimetable; //returns only the base timetable (without bookings etc.) (default:false)
    private boolean showBooking; //returns the period's booking info if available (default: false)
    private boolean showInfo; //eturns the period information if available (default: false)
    private boolean showSubstText; //returns the Untis substitution text if available (default: false)
    private boolean showLsText;  //returns the text of the period's lesson (default: false)
    private boolean showLsNumber; //urns the number of the period's lesson (default: false)
    private boolean showStudentgroup;  //eturns the name(s) of the studentgroup(s) (default: false)

    public RequestElement getElement() {
        return element;
    }

    public void setElement(RequestElement element) {
        this.element = element;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    public RequestField[] getKlasseFields() {
        return klasseFields;
    }

    public void setKlasseFields(final RequestField[] fields) {
        this.klasseFields = fields;
    }

    public RequestField[] getSubjectFields() {
        return subjectFields;
    }

    public void setSubjectFields(final RequestField[] fields) {
        this.subjectFields = fields;
    }

    public RequestField[] getTeacherFields() {
        return teacherFields;
    }

    public void setTeacherFields(final RequestField[] fields) {
        this.teacherFields = fields;
    }

    public RequestField[] getRoomFields() {
        return roomFields;
    }

    public void setRoomFields(final RequestField[] roomFields) {
        this.roomFields = roomFields;
    }

    public boolean isOnlyBaseTimetable() {
        return onlyBaseTimetable;
    }

    public void setOnlyBaseTimetable(final boolean onlyBaseTimetable) {
        this.onlyBaseTimetable = onlyBaseTimetable;
    }

    public boolean isShowBooking() {
        return showBooking;
    }

    public void setShowBooking(final boolean showBooking) {
        this.showBooking = showBooking;
    }

    public boolean isShowInfo() {
        return showInfo;
    }

    public void setShowInfo(final boolean showInfo) {
        this.showInfo = showInfo;
    }

    public boolean isShowSubstText() {
        return showSubstText;
    }

    public void setShowSubstText(final boolean showSubstText) {
        this.showSubstText = showSubstText;
    }

    public boolean isShowLsText() {
        return showLsText;
    }

    public void setShowLsText(final boolean showLsText) {
        this.showLsText = showLsText;
    }

    public boolean isShowLsNumber() {
        return showLsNumber;
    }

    public void setShowLsNumber(final boolean showLsNumber) {
        this.showLsNumber = showLsNumber;
    }

    public boolean isShowStudentgroup() {
        return showStudentgroup;
    }

    public void setShowStudentgroup(final boolean showStudentgroup) {
        this.showStudentgroup = showStudentgroup;
    }

}
