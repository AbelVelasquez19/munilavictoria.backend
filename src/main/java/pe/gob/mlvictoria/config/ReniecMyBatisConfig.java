package pe.gob.mlvictoria.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
        basePackages = "pe.gob.mlvictoria.consultapide.mapper",
        sqlSessionTemplateRef = "sigmunSqlSessionTemplate"
)
public class ReniecMyBatisConfig {
}
