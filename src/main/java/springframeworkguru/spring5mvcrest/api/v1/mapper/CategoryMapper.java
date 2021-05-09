package springframeworkguru.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import springframeworkguru.spring5mvcrest.api.v1.model.CategoryDTO;
import springframeworkguru.spring5mvcrest.domain.Category;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "id", source = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
