package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.CategoryDevice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDeviceMapper extends ObjectMapper<CategoryDevice, CategoryDeviceDTO> {

    CategoryDeviceMapper converter = Mappers.getMapper(CategoryDeviceMapper.class);

    @Override
    public CategoryDeviceDTO convertToDto(CategoryDevice categoryDevice);

    @Override
    public CategoryDevice convertToEntity(CategoryDeviceDTO categoryDeviceDTO);
}
