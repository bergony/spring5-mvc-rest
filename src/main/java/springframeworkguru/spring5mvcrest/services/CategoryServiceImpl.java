package springframeworkguru.spring5mvcrest.services;

import org.springframework.stereotype.Service;
import springframeworkguru.spring5mvcrest.api.v1.mapper.CategoryMapper;
import springframeworkguru.spring5mvcrest.api.v1.model.CategoryDTO;
import springframeworkguru.spring5mvcrest.controllers.v1.CategoryController;
import springframeworkguru.spring5mvcrest.controllers.v1.CustomerController;
import springframeworkguru.spring5mvcrest.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return   categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    private String getCategoryUrl(Long id) {
        return CategoryController.BASE_URL + "/" + id;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {

        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
