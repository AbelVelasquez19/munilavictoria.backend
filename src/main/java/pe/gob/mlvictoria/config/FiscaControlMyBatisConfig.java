package pe.gob.mlvictoria.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
        //basePackages = "pe.gob.mlvictoria.fiscalizacioncontrol.mapper",
        basePackages = {
                "pe.gob.mlvictoria.fiscalizacioncontrol.mapper",
                "pe.gob.mlvictoria.talleres.mapper",
                "pe.gob.mlvictoria.complejo.mapper",
        },
        sqlSessionTemplateRef = "sigmunSqlSessionTemplate"
)
public class FiscaControlMyBatisConfig {
}
