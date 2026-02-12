package tech.qmates.birthdaygreetings;

import org.junit.jupiter.api.Test;

public class BirthdayServiceTest {


    @Test
    void givenTodayIsEmployeesBirthday_whenGreeting_thenSendMessage() {
        BirthdayService birthdayService = new BirthdayService(mailServer);

        birthdayService.sendGreetings(today());

        assertThat(mailServer.sentMessages()).hasSize(1);
    }
}
