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
import org.thespheres.connect.untis.model.RequestOptions;

/**
 *
 * @author boris.heithecker@gmx.net
 */
public class LessonsResponse extends APIResponse {

    public static LessonsResponse request(final LocalDate start, final LocalDate end, final WebUntisAPIClient client) throws IOException {
        final RequestOptions.RequestField[] ff = new RequestOptions.RequestField[]{RequestOptions.RequestField.ID, RequestOptions.RequestField.NAME, RequestOptions.RequestField.LONG_NAME, RequestOptions.RequestField.EXTERNAL_KEY};
        final APIRequest req = new APIRequest("getLessons");
        req.getRequestOptions().setStartDate(start);
        req.getRequestOptions().setEndDate(end);

        req.getRequestOptions().setKlasseFields(ff);
        req.getRequestOptions().setSubjectFields(ff);
        req.getRequestOptions().setTeacherFields(ff);
        return client.postRequest(req, LessonsResponse.class);
    }

    public void dump(final PrintStream out) {
        Arrays.stream(results)
                .filter(r -> r.getLessonNumber() != null && APIResult.LessonType.LESSON.equals(r.getLstype()))
                .sorted(Comparator.comparingInt(APIResult::getLessonNumber))
                .map(this::toString)
                .forEach(out::println);
    }

    private String toString(final APIResult r) {
        final StringJoiner sj = new StringJoiner("; ");
        sj
                .add(r.getId())
                .add(Integer.toString(r.getLessonNumber()));
        if (r.getActivityType() != null) {
            sj.add(r.getActivityType());
        }
        if (r.getKlassen() != null) {
            final String kl = Arrays.stream(r.getKlassen())
                    .map(k -> k.getLongname())
                    .collect(Collectors.joining(", ", "Classes: ", ""));
            sj.add(kl);
        }
        if (r.getTeachers() != null) {
            final String te = Arrays.stream(r.getTeachers())
                    .map(k -> k.getName())
                    .collect(Collectors.joining(", ", "Teachers: ", ""));
            sj.add(te);
        }
        if (r.getSubjects() != null) {
            final String su = Arrays.stream(r.getSubjects())
                    .map(k -> k.getName())
                    .collect(Collectors.joining(", ", "Subjects: ", ""));
            sj.add(su);
        }
        return sj.toString();
    }

}
