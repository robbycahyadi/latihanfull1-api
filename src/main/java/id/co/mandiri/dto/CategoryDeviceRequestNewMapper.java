package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.CategoryDevice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDeviceRequestNewMapper extends ObjectMapper<CategoryDevice, CategoryDeviceDTO.CategoriDeviceRequestNewDTO> {

    CategoryDeviceRequestNewMapper converter = Mappers.getMapper(CategoryDeviceRequestNewMapper.class);

}
