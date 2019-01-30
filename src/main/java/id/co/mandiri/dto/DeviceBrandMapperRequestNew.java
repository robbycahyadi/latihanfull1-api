package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.DeviceBrand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceBrandMapperRequestNew extends ObjectMapper<DeviceBrand, DeviceBrandDTO.DeviceBrandRequestNewDTO> {

    DeviceBrandMapperRequestNew converter = Mappers.getMapper(DeviceBrandMapperRequestNew.class);

}
