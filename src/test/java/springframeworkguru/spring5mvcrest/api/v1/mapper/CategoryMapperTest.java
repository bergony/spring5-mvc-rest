package springframeworkguru.spring5mvcrest.api.v1.mapper;

import org.junit.jupiter.api.Test;
import springframeworkguru.spring5mvcrest.api.v1.model.CategoryDTO;
import springframeworkguru.spring5mvcrest.domain.Category;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    public static final String NAME = "Bergony";
    public static final long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDto() throws Exception {

        //Given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //When
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(java.util.Optional.ofNullable(Long.valueOf(ID)), java.util.Optional.of(categoryDTO.getId()));
        assertEquals(NAME, categoryDTO.getName());

    }

}