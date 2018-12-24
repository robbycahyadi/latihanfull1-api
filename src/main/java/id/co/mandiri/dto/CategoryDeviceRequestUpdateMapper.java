package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.CategoryDevice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDeviceRequestUpdateMapper extends ObjectMapper<CategoryDevice, CategoryDeviceDTO.CategoryDeviceRequestUpdateDTO> {

    CategoryDeviceRequestUpdateMapper converter = Mappers.getMapper(CategoryDeviceRequestUpdateMapper.class);
}
