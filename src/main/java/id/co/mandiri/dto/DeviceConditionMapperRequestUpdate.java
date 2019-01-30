package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.DeviceCondition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceConditionMapperRequestUpdate extends ObjectMapper<DeviceCondition, DeviceConditionDTO.DeviceConditionRequestUpdateDTO> {

    DeviceConditionMapperRequestUpdate converter = Mappers.getMapper(DeviceConditionMapperRequestUpdate.class);
}
