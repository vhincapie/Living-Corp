package co.edu.unbosque.serviciosapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI api() {
		return new OpenAPI().info(new Info()
				.title("Servicios API")
				.version("1.0")
				.description("API para manejo de proveedores LivingCorp.")
				.termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html")))
			.servers(List.of(new Server().url("https://localhost:8888/proveedor/api")));
	}

}
