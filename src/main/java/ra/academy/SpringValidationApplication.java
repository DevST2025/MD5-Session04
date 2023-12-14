package ra.academy;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class SpringValidationApplication {
    private final String firebaseConfigPath = "firebase-config.json";

    public static void main(String[] args) {
        SpringApplication.run(SpringValidationApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper () {
        return new ModelMapper();
    }
    @Bean
    public Storage storage() throws IOException {
        Resource resource = new ClassPathResource(firebaseConfigPath);
        InputStream serviceAccount = resource.getInputStream();

        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();
    }
}
