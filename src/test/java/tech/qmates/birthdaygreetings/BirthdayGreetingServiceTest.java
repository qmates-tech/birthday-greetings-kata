package tech.qmates.birthdaygreetings;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BirthdayGreetingServiceTest {


    @Test
    void givenEmployeeBirthday_ThanSendEmail() {

        EmailService emailService = mock(EmailService.class);
        BirthdayGreetingService birthdayService = new BirthdayGreetingService(emailService);

        birthdayService.sendGreetings();

        verify(emailService.sendEmail(any()));
    }
}
