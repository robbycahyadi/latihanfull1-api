package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.DeviceUnitCapacity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceUnitCapacityMapperRequestUpdate extends ObjectMapper<DeviceUnitCapacity, DeviceUnitCapacityDTO.DeviceUnitCapacityRequestUpdateDTO> {

    DeviceUnitCapacityMapperRequestUpdate converter = Mappers.getMapper(DeviceUnitCapacityMapperRequestUpdate.class);
}
