package pe.gob.mlvictoria.complejo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.complejo.dto.dashboard.DashboardIngresosResponse;

import java.util.List;

@Mapper
public interface DashboardMapper {
    @Select("""
         EXEC complejoDeportivo.sp_dashboard_ingresos_reserva_demanda_reporte
              @opcion = #{opcion},
              @fechaInicio = #{fechaInicio},
              @fechaFin = #{fechaFin};
        """)
    @Options(statementType = StatementType.CALLABLE)
    List<DashboardIngresosResponse> dashboardIngresos(@Param("opcion") int opcion, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
}
