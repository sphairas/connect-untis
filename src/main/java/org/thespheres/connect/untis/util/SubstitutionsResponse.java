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
import org.thespheres.connect.untis.model.LocalDateAdapter;

/**
 *
 * @author boris.heithecker@gmx.net
 */
public class SubstitutionsResponse extends APIResponse {

    public static SubstitutionsResponse request(final LocalDate start, final LocalDate end, final WebUntisAPIClient client) throws IOException {
        final APIRequest req = new APIRequest("getSubstitutions");
        req.setStringParameter("startDate", start.format(LocalDateAdapter.DTF));
        req.setStringParameter("endDate", end.format(LocalDateAdapter.DTF));
        req.setStringParameter("departmentId", "0");
        String res = client.readRequest(req);
        return client.postRequest(req, SubstitutionsResponse.class);
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
                .add(r.getDate().toString())
                .add(r.getStartTime().toString())
                .add(Integer.toString(r.getLessonId()));
        if (r.getSubstitutionType() != null) {
            sj.add(r.getSubstitutionType().name());
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
        if (r.getSubstitutionText() != null) {
            sj.add(r.getSubstitutionText());
        }
        if (r.getReschedule() != null) {
            sj.add("Reschudule: " + r.getReschedule().getDate().toString());
        }
        return sj.toString();
    }
}
