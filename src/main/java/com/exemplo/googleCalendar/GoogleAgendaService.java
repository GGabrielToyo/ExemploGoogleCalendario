package com.exemplo.googleCalendar;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleAgendaService {
    private String APPLICATION_NAME = "Exemplo de integração com agenda do Google";

    @Value("${google.service.account.key.file}")
    private String SERVICE_ACCOUNT_KEY_FILE;

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private Calendar service;

    @Bean
    public void GoogleAgenda() throws GeneralSecurityException, IOException {
        service = getCalendarService();
    }

    private Calendar getCalendarService() throws GeneralSecurityException, IOException {
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_FILE))
                .createScoped(Collections.singleton(CalendarScopes.CALENDAR));

        return new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public void criarAgendamento(String calendarId, String summary, String location, String description, String startDateTime, String endDateTime) throws Exception {
        Event event = new Event()
                .setSummary(summary)
                .setLocation(location)
                .setDescription(description);

        event.setStart(formatarDataParaEventDateTime(startDateTime));
        event.setEnd(formatarDataParaEventDateTime(endDateTime));

        service.events().insert(calendarId, event).execute();
    }

    private EventDateTime formatarDataParaEventDateTime(String date) {
        DateTime dateTime = new DateTime(date);
        EventDateTime dateEvent = new EventDateTime()
                .setDateTime(dateTime)
                .setTimeZone("America/Sao_Paulo");

        return dateEvent;
    }
}
