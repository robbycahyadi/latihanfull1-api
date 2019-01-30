package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.DeviceBrand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceBrandMapperRequestUpdate extends ObjectMapper<DeviceBrand, DeviceBrandDTO.DeviceBrandRequestUpdateDTO> {

    DeviceBrandMapperRequestUpdate converter = Mappers.getMapper(DeviceBrandMapperRequestUpdate.class);
}
