/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thespheres.connect.untis;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.thespheres.connect.untis.model.APIRequest;
import org.thespheres.connect.untis.model.APIResponse;
import org.thespheres.connect.untis.model.APIResult;
import org.thespheres.connect.untis.model.LastImportTimeAPIResponse;
import org.thespheres.connect.untis.model.RequestElement;
import org.thespheres.connect.untis.model.SingleResultAPIResponse;
import org.thespheres.connect.untis.util.LessonsResponse;
import org.thespheres.connect.untis.util.TimetableResponse;
import org.thespheres.connect.untis.util.SubstitutionsResponse;

/**
 *
 * @author boris.heithecker@gmx.net
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) throws Exception {
        final Path env = Paths.get(".env");
        final Properties props = new Properties();
        try (final InputStream is = Files.newInputStream(env)) {
            props.load(is);
        }
        final String href = props.getProperty("WEBUNTIS_URL");
        final String account = props.getProperty("WEBUNTIS_USER");
        final String password = props.getProperty("WEBUNTIS_PASSWORD");
        if (href == null || account == null || password == null) {
            throw new IllegalStateException("Invalid or incomplete credentials.");
        }

        final WebUntisAPIClient client = new WebUntisAPIClient(href);
        final LocalDate start = LocalDate.of(2020, Month.AUGUST, 27);
        final LocalDate end = LocalDate.now();

        //Authenticate
        final APIRequest authReq = new APIRequest("authenticate");
        authReq.setStringParameter("user", account);
        authReq.setStringParameter("password", password);
        authReq.setStringParameter("client", "sphairas");
        final SingleResultAPIResponse authResp = client.postRequest(authReq, SingleResultAPIResponse.class);
        System.out.println(authResp.getResult().getSessionId());

        //Get last import time
        final APIRequest litReq = new APIRequest("getLatestImportTime");
        final LastImportTimeAPIResponse litResp = client.postRequest(litReq, LastImportTimeAPIResponse.class);
        final LocalDateTime lit = LocalDateTime.ofInstant(Instant.ofEpochMilli(litResp.getTimestamp()), ZoneId.systemDefault());
        System.out.println(lit.toString());

        //Get all lessons
        final LessonsResponse lessons = LessonsResponse.request(start, end, client);
        lessons.dump(System.out);

        //Get all teachers' timetables
        final APIRequest teachReq = new APIRequest("getTeachers");
        final APIResponse teachResp = client.postRequest(teachReq, APIResponse.class);
        final Map<String, TimetableResponse> ls = new HashMap<>();
        for (final APIResult res : teachResp.getResults()) {
            final RequestElement re = new RequestElement(res.getId(), APIRequest.TimetableType.TEACHER, RequestElement.KeyType.ID);
            final TimetableResponse sr = TimetableResponse.request(re, start, end, client);
            System.out.println("==========" + res.getName() + "==========");
            sr.dump(System.out);
            ls.put(res.getName(), sr);
        }

        //Get all substitutions
        
        
        
        
        final SubstitutionsResponse sub = SubstitutionsResponse.request(start, end, client);
        sub.dump(System.out);

        //logout
        final APIRequest logout = new APIRequest("logout");
        String res = client.readRequest(logout);

    }

}
