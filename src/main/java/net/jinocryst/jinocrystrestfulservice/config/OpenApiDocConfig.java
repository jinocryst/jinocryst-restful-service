package net.jinocryst.jinocrystrestfulservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "jinocryst-restful-service API Doc",
                description = "description",
                version = "0.0.1"
        )
)
@Configuration
@RequiredArgsConstructor
public class OpenApiDocConfig {

    @Bean
    public GroupedOpenApi customOpenApi() {

        // 해당 paths 에 해당하는 API 만 문서화함
        String[] paths = {"/users/**", "/admin/**"};

        return GroupedOpenApi.builder()
                .group("User 도메인 API")
                .pathsToMatch(paths)
                .build();
    }
}
