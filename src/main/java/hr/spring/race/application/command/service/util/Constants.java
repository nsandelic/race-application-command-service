package hr.spring.race.application.command.service.util;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

    // Application constants
    @Value("${spring.application.name}")
    public static String APP_NAME;
    public static final String DATE_FORMAT = "dd-MM-yyyy";

    // URLs
    public static final String BASE_API_URL = "https://localhost:8080";

}
