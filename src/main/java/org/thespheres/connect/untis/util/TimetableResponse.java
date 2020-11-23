/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thespheres.connect.untis.util;

import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.thespheres.connect.untis.WebUntisAPIClient;
import org.thespheres.connect.untis.model.APIRequest;
import org.thespheres.connect.untis.model.APIResponse;
import org.thespheres.connect.untis.model.APIResult;
import org.thespheres.connect.untis.model.RequestElement;
import org.thespheres.connect.untis.model.RequestOptions;

/**
 *
 * @author boris.heithecker@gmx.net
 */
public class TimetableResponse extends APIResponse {

    public static TimetableResponse request(final RequestElement re, final LocalDate start, final LocalDate end, final WebUntisAPIClient client) throws IOException {
        final RequestOptions.RequestField[] ff = new RequestOptions.RequestField[]{RequestOptions.RequestField.ID, RequestOptions.RequestField.NAME, RequestOptions.RequestField.LONG_NAME, RequestOptions.RequestField.EXTERNAL_KEY};
        final APIRequest req = new APIRequest("getTimetable");
        req.getRequestOptions().setElement(re);
        req.getRequestOptions().setStartDate(start);
        req.getRequestOptions().setEndDate(end);
        req.getRequestOptions().setShowLsNumber(true);
        req.getRequestOptions().setShowStudentgroup(true);
        req.getRequestOptions().setShowSubstText(true);
        req.getRequestOptions().setShowLsText(true);
        req.getRequestOptions().setKlasseFields(ff);
        req.getRequestOptions().setSubjectFields(ff);
        req.getRequestOptions().setTeacherFields(ff);
        req.getRequestOptions().setRoomFields(ff);
        return client.postRequest(req, TimetableResponse.class);
    }

    public void dump(final PrintStream out) {
        Arrays.stream(results)
                .sorted(Comparator.comparing(APIResult::getDate))
                .sorted(Comparator.comparing(APIResult::getStartTime))
                .map(this::toString)
                .forEach(out::println);
    }

    private String toString(final APIResult r) {
        final StringJoiner sj = new StringJoiner("; ");
        sj
                .add(r.getId())
                .add(r.getDate().toString())
                .add(r.getStartTime().toString())
                .add(Integer.toString(r.getLessonNumber()));
        if (r.getLstype() != null) {
            sj.add(r.getLstype().name());
        }
        if (r.getKl() != null) {
            final String kl = Arrays.stream(r.getKl())
                    .map(k -> k.getLongname())
                    .collect(Collectors.joining(", ", "Classes: ", ""));
            sj.add(kl);
        }
        if (r.getTe() != null) {
            final String te = Arrays.stream(r.getTe())
                    .map(k -> k.getName())
                    .collect(Collectors.joining(", ", "Teachers: ", ""));
            sj.add(te);
        }
        if (r.getSu() != null) {
            final String su = Arrays.stream(r.getSu())
                    .map(k -> k.getName())
                    .collect(Collectors.joining(", ", "Subjects: ", ""));
            sj.add(su);
        }
        if (r.getInfo() != null) {
            sj.add(r.getInfo());
        }
        if (r.getLstext() != null) {
            sj.add(r.getLstext());
        }
        if (r.getSubstText() != null) {
            sj.add(r.getSubstText());
        }
        return sj.toString();
    }
}
