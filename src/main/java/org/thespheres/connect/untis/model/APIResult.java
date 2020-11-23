/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thespheres.connect.untis.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author boris.heithecker@gmx.net
 */
public class APIResult {

    public enum LessonType {
        @SerializedName("ls")
        LESSON,
        @SerializedName("oh")
        OFFICE_HOUR,
        @SerializedName("sb")
        STANDBY,
        @SerializedName("bs")
        BREAK_SUPERVISION,
        @SerializedName("ex")
        EXAMINATION
    }

    public enum Code {
        @SerializedName("cancelled")
        CANCELLED,
        @SerializedName("irregular")
        EXTRA,
        @SerializedName("")
        EMPTY
    }

    public enum SubstitutionType {
        @SerializedName("cancel")
        CANCELLED, //cancellation               
        @SerializedName("subst")
        TEACHER_SUBSTITUTION,
        @SerializedName("add")
        EXTRA, //additional period
        @SerializedName("shift")
        DEFERRAL, //shifted period
        @SerializedName("rmchg")
        ROOM_CHANGE,
        @SerializedName("rmlk")
        LOCKED, //locked period
        @SerializedName("bs")
        BREAK_SUPERVISION,
        @SerializedName("oh")
        OFFICE_HOUR,
        @SerializedName("sb")
        STANDBY,
        @SerializedName("other")
        OTHER,//other foreign substitutions
        @SerializedName("free")
        FREE_PERIOD,
        @SerializedName("exam")
        EXAMINATION,
        @SerializedName("ac")
        ACTIVITY,
        @SerializedName("holi")
        HOLIDAY, //holi holiday
        @SerializedName("stxt")
        TEXT //stxt substitution text
    }

    private String id;
    private String sessionId;
    private int personType;
    private int personId;
    private int klasseId;
    private String name;
    private String foreName;
    private String longName;
    private String foreColor;
    private String backColor;
    private String key;
    private String gender;
    private int did;
    private int[] dids;
    @JsonAdapter(LocalDateAdapter.class)
    private LocalDate date;
    @JsonAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @JsonAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    @JsonAdapter(LocalTimeAdapter.class)
    private LocalTime startTime;
    @JsonAdapter(LocalTimeAdapter.class)
    private LocalTime endTime;
    private LessonType lstype;
    private Integer lessonNumber;
    private Integer lsnumber;
    @SerializedName("hwp")
    private int hoursPerWeek;
    private String studentgroup;
    private String activityType;
    private Code code;
    private Referenced[] kl;
    private Referenced[] te;
    private Referenced[] su;
    private Referenced[] ro;
    private Referenced[] klassen;
    private Referenced[] teachers;
    private Referenced[] subjects;
    private String lstext; //text of the period's lesson
    private String statflags;
    private String info; //period information
    private String substText;
    private String sg; //name of the period's student group
    private String bkRemark; //remark of the period's booking (optional)
    private String bkText; //text of the period's booking (optional)

    //Substitution object
    private int lsid;
    private SubstitutionType type;
    @SerializedName("text")
    private String substitutionText;
    private Reschedule reschedule;

    APIResult() {
    }

    public String getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public int getPersonType() {
        return personType;
    }

    public int getPersonId() {
        return personId;
    }

    public int getKlasseId() {
        return klasseId;
    }

    public String getName() {
        return name;
    }

    public String getForeName() {
        return foreName;
    }

    public String getLongName() {
        return longName;
    }

    public int[] getDids() {
        return dids;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LessonType getLstype() {
        return lstype;
    }

    public Integer getLessonNumber() {
        if (lessonNumber != null) {
            if (lsnumber != null) {
                throw new IllegalStateException("Either lessonNumber or lsnumber expected to be null.");
            }
            return lessonNumber;
        } else {
            return lsnumber;
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public String getStudentgroup() {
        return studentgroup;
    }

    public String getActivityType() {
        return activityType;
    }

    public Code getCode() {
        return code;
    }

    public Referenced[] getKlassen() {
        return klassen;
    }

    public Referenced[] getTeachers() {
        return teachers;
    }

    public Referenced[] getSubjects() {
        return subjects;
    }

    public String getLstext() {
        return lstext;
    }

    public String getStatflags() {
        return statflags;
    }

    public String getInfo() {
        return info;
    }

    public String getSubstText() {
        return substText;
    }

    public String getSg() {
        return sg;
    }

    public String getBkRemark() {
        return bkRemark;
    }

    public String getBkText() {
        return bkText;
    }

    public Referenced[] getKl() {
        return kl;
    }

    public Referenced[] getTe() {
        return te;
    }

    public void setTe(Referenced[] te) {
        this.te = te;
    }

    public Referenced[] getSu() {
        return su;
    }

    public void setSu(Referenced[] su) {
        this.su = su;
    }

    public Referenced[] getRo() {
        return ro;
    }

    public void setRo(Referenced[] ro) {
        this.ro = ro;
    }

    public String getForeColor() {
        return foreColor;
    }

    public String getBackColor() {
        return backColor;
    }

    public String getKey() {
        return key;
    }

    public String getGender() {
        return gender;
    }

    public int getDid() {
        return did;
    }

    public int getLessonId() {
        return lsid;
    }

    public SubstitutionType getSubstitutionType() {
        return type;
    }

    public String getSubstitutionText() {
        return substitutionText;
    }

    public Reschedule getReschedule() {
        return reschedule;
    }

}
