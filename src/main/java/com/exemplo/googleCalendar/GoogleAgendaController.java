package com.exemplo.googleCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/google-calendar")
@RestController
public class GoogleAgendaController {

    @Autowired
    private GoogleAgendaService googleAgendaService;

    private String calendarId = "<Calendar-ID>";

    @PostMapping("/agendar")
    public ResponseEntity teste() {
        try {
            googleAgendaService.criarAgendamento(
                    calendarId,
                    "Teste Agendamento",
                    "Local da reunião",
                    "Descrição da reunião",
                    "2024-09-18T12:00:00-03:00",
                    "2024-09-18T13:00:00-03:00"
            );
            return ResponseEntity.ok().body("Agendamento criado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao criar evento na agenda do Google");
        }
    }
}
