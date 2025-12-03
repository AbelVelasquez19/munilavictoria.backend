package pe.gob.mlvictoria.pagolinea.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.CreateIntentReq;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.CreateIntentRes;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.ReciboResultDTO;
import pe.gob.mlvictoria.pagolinea.dto.niuviz.UpdateAfterAuthRe;
import pe.gob.mlvictoria.pagolinea.dto.pago.DatosContiResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.DatosContriRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.NumCompraRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.pago.NumCompraResponseDTO;
import pe.gob.mlvictoria.pagolinea.dto.recibo.*;
import pe.gob.mlvictoria.pagolinea.dto.validarrecibo.ReciboRequestDTO;
import pe.gob.mlvictoria.pagolinea.dto.validarrecibo.ReciboResponseDTO;

import java.util.List;

@Mapper
public interface PagoLiniaMapper {

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC Caja.sp_EstCta
        @codigo = #{dto.codigo},
        @annos = #{dto.annos},
        @tipos = #{dto.tipos},
        @tiporec = #{dto.tipoRec},
        @perio = #{dto.perio},
        @predio = #{dto.predio},
        @criterio = #{dto.criterio},
        @usuario = #{dto.usuario},
        @idrecibo_group = #{dto.idreciboGroup}
    """)
    List<ReciboResponseDTO> validarRecibo (@Param("dto") ReciboRequestDTO dto);

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC dbo.pxConsultasWeb2
        @msquery = #{dto.opcion},
        @paramt1 = #{dto.codigo}
    """)
    DatosContiResponseDTO obtenerDatosContribuyente (@Param("dto") DatosContriRequestDTO dto);

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC Cepos.sp_Infracciones_info
        @opc = #{dto.opcion}
    """)
    NumCompraResponseDTO obtenerNumeroCompra (@Param("dto") NumCompraRequestDTO dto);

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
    DECLARE @id BIGINT;
    EXEC caja.sp_recibo_crear_intento
      @purchaseNumber = #{dto.purchaseNumber},
      @amount         = #{dto.amount,jdbcType=DECIMAL},
      @currency       = #{dto.currency},
      @codigo         = #{dto.codigo},
      @ordenanza      = #{dto.ordenanza},
      @usuario        = #{dto.usuario},
      @sessionKey     = #{dto.sessionKey},
      @ip             = #{dto.ip},
      @userAgent      = #{dto.userAgent},
      @detaDataJson   = #{dto.detaDataJson},
      @reciboId       = @id OUTPUT,
      @modulo         = #{dto.moduloAgente};
    SELECT reciboId = @id;
  """)
    CreateIntentRes crearIntento(@Param("dto") CreateIntentReq dto);

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
    DECLARE @rows INT;
    EXEC caja.sp_recibo_actualizar_despues_autorizacion
      @reciboId          = #{dto.reciboId},
      @intento           = #{dto.intento},
      @purchase_number   = #{dto.purchase_number},
      @tokenId           = #{dto.tokenId},
      @status            = #{dto.status},
      @ordenanza         = #{dto.ordenanza},
      @authRawJson       = #{dto.authRawJson},
      @rowsAffected      = @rows OUTPUT;
      SELECT @rows AS rowsAffected;
    """)
    Integer actualizarDespuesDeAuth(@Param("dto") UpdateAfterAuthRe dto);

    @Options(statementType = StatementType.CALLABLE)
    @ConstructorArgs({
        @Arg(column = "id",           javaType = Long.class),
        @Arg(column = "purchaseNumber", javaType = String.class),
        @Arg(column = "codigo", javaType = String.class),
        @Arg(column = "amount",       javaType = java.math.BigDecimal.class),
        @Arg(column = "currency",     javaType = String.class),
        @Arg(column = "status",       javaType = String.class),
        @Arg(column = "ordenanza",    javaType = String.class),
        @Arg(column = "detaData",    javaType = String.class),
        @Arg(column = "authRaw",      javaType = String.class),
        @Arg(column = "createdAt",   javaType = String.class),
        @Arg(column = "updatedAt",   javaType = String.class)
    })
    @Select("""
    EXEC caja.sp_recibo_obtener_resultado
      @reciboId = #{reciboId},
      @purchaseNumber = #{purchaseNumber}
    """)
    ReciboResultDTO obtenerResultadoVisa(@Param("reciboId") Long reciboId, @Param("purchaseNumber") String purchaseNumber);

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC Caja.sp_Genera_Recibos
            @codigo = #{dto.codigo},
            @cajero = #{dto.cajero},
            @dataxml = #{dto.dxml},
            @tipo_pago = #{dto.formaPago},
            @criterio = #{dto.criterio},
            @banco = #{dto.cmbBanco},
            @tarjeta = #{dto.cmbTarjeta},
            @num_cheque = '',
            @observa = #{dto.txtObservacion},
            @operador = #{dto.nomCaja},
            @estacion = #{dto.estacion},
            @monto = #{dto.txtCobrar},
            @montoacuenta = #{dto.txtEfectivo}
    """)
    List<GenerarReciboResponseDTO> generarRecibo(@Param("dto") GenerarReciboRequestDTO dto);

    @Options(statementType = StatementType.CALLABLE)
    @Select("""
        EXEC Caja.sp_pasarela
            @buscar             = #{dto.buscar},
            @num_ingr           = #{dto.numIngr},
            @codigo             = #{dto.codigo},
            @purchaseNumber     = #{dto.purchaseNumber},
            @currency           = #{dto.currency},
            @transaction_date   = #{dto.transactionDate},
            @terminal           = #{dto.terminal},
            @action_code        = #{dto.actionCode},
            @trace_number       = #{dto.traceNumber},
            @eci_description    = #{dto.eciDescription},
            @eci                = #{dto.eci},
            @signature          = #{dto.signature},
            @brand              = #{dto.brand},
            @card               = #{dto.card},
            @merchant           = #{dto.merchant},
            @status             = #{dto.status},
            @adquirente         = #{dto.adquirente},
            @action_description = #{dto.actionDescription},
            @id_unico           = #{dto.idUnico},
            @amount             = #{dto.amount},
            @process_code       = #{dto.processCode},
            @transaction_id     = #{dto.transactionId},
            @authorization_code = #{dto.authorizationCode}
        """)
    List<GenerarReciboResponseDTO> generarPasarela(@Param("dto") TransaccionRequestDTO dto);

    @Select("""
      SELECT TOP (1) id, purchase_number, status, intento
      FROM caja.recibo_niubiz
      WHERE purchase_number = #{dto.purchaseNumber}
      """)
    @Results({
        @Result(column="id", property="id"),
        @Result(column="purchase_number", property="purchaseNumber"),
        @Result(column="status", property="status")
    })
    NumeroCompraResponseDTO consultarNumeroCompra(@Param("dto") NumeroCompraRequestDTO dto);

    @Update("""
        UPDATE caja.recibo_niubiz
        SET intento = 2
        WHERE purchase_number = #{dto.purchaseNumber}
    """)
    Integer actualizarIntento(@Param("dto") NumeroCompraRequestDTO dto);
}
