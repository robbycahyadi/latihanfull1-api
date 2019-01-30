package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.DeviceLoanStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceLoanStatusMapperRequestUpdate extends ObjectMapper<DeviceLoanStatus, DeviceLoanStatusDTO.DeviceLoanStatusRequestUpdateDTO> {

    DeviceLoanStatusMapperRequestUpdate converter = Mappers.getMapper(DeviceLoanStatusMapperRequestUpdate.class);
}
