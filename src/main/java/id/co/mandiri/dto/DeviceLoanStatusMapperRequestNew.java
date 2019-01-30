package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.DeviceLoanStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceLoanStatusMapperRequestNew extends ObjectMapper<DeviceLoanStatus, DeviceLoanStatusDTO.DeviceLoanStatusRequestNewDTO> {

    DeviceLoanStatusMapperRequestNew converter = Mappers.getMapper(DeviceLoanStatusMapperRequestNew.class);

}
