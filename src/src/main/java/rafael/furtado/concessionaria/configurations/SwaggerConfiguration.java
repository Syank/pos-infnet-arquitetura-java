package rafael.furtado.concessionaria.configurations;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI swaggerAPI() {
        OpenAPI openAPI = new OpenAPI();

        Contact contact = new Contact();
        contact.name("Equipe de Desenvolvimento");
        contact.email("rafael.furtado@email.com");

        Info apiInfo = new Info();

        apiInfo.title("API: Concessionária de veículos global");
        apiInfo.version("1.0");
        apiInfo.description("API para controle de concessionária de veículos");
        apiInfo.contact(contact);

        openAPI.info(apiInfo);

        return openAPI;
    }

}
