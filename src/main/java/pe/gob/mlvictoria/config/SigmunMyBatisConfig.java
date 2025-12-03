package pe.gob.mlvictoria.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
        basePackages = "pe.gob.mlvictoria.accesosigmun.mapper.sigmun",
        sqlSessionTemplateRef = "sigmunSqlSessionTemplate"
)
public class SigmunMyBatisConfig {
}
