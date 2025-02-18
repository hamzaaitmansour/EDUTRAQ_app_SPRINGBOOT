package fullStack.template;

import fullStack.template.models.Etudiant;
import fullStack.template.repository.EtudiantRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);

	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
