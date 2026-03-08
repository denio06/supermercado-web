package com.supermercado.supermercado_web;

import com.supermercado.supermercado_web.model.Usuario;
import com.supermercado.supermercado_web.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SupermercadoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupermercadoWebApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (usuarioRepository.findByEmail("admin@sabugomercadinho.com").isEmpty()) {
				Usuario admin = new Usuario();
				admin.setNome("Admin");
				admin.setEmail("admin@sabugomercadinho.com");
				admin.setSenha(passwordEncoder.encode("admin123"));
				admin.setPerfil("ROLE_ADMIN");
				usuarioRepository.save(admin);
				System.out.println("✅ Admin criado com sucesso!");
			}
		};
	}
}