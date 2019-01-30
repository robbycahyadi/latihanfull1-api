package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.DeviceCondition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceConditionMapperRequestNew extends ObjectMapper<DeviceCondition, DeviceConditionDTO.DeviceConditionRequestNewDTO> {

    DeviceConditionMapperRequestNew converter = Mappers.getMapper(DeviceConditionMapperRequestNew.class);

}
