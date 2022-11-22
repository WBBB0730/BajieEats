package cn.edu.szu.Bajie.converter;

import cn.edu.szu.Bajie.dto.result.CollectDishResultDto;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.Dish;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-21T23:39:48+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class DishConverterImpl implements DishConverter {

    @Override
    public SimpleDishResultDto dish2simpleDish(Dish dish) {
        if ( dish == null ) {
            return null;
        }

        SimpleDishResultDto simpleDishResultDto = new SimpleDishResultDto();

        simpleDishResultDto.setDishId( dish.getDishId() );
        simpleDishResultDto.setDishName( dish.getDishName() );
        simpleDishResultDto.setDishImage( dish.getDishImage() );
        simpleDishResultDto.setPrice( dish.getPrice() );
        simpleDishResultDto.setTags( dish.getTags() );
        simpleDishResultDto.setSort( dish.getSort() );
        simpleDishResultDto.setScore( dish.getScore() );

        return simpleDishResultDto;
    }

    @Override
    public DishDetailResultDto dish2DishDetail(Dish dish) {
        if ( dish == null ) {
            return null;
        }

        DishDetailResultDto dishDetailResultDto = new DishDetailResultDto();

        dishDetailResultDto.setDishId( dish.getDishId() );
        dishDetailResultDto.setDishName( dish.getDishName() );
        dishDetailResultDto.setDishImage( dish.getDishImage() );
        dishDetailResultDto.setPrice( dish.getPrice() );
        dishDetailResultDto.setTags( dish.getTags() );
        dishDetailResultDto.setWinId( dish.getWinId() );
        dishDetailResultDto.setDeleteStatus( dish.getDeleteStatus() );
        dishDetailResultDto.setRecommendStatus( dish.getRecommendStatus() );
        dishDetailResultDto.setSort( dish.getSort() );
        dishDetailResultDto.setIngredients( dish.getIngredients() );
        dishDetailResultDto.setScore( dish.getScore() );
        dishDetailResultDto.setOnSaleStatus( dish.getOnSaleStatus() );
        dishDetailResultDto.setCreateTime( dish.getCreateTime() );
        dishDetailResultDto.setUpdateTime( dish.getUpdateTime() );

        return dishDetailResultDto;
    }

    @Override
    public CollectDishResultDto dish2ColletDish(Dish dish) {
        if ( dish == null ) {
            return null;
        }

        CollectDishResultDto collectDishResultDto = new CollectDishResultDto();

        collectDishResultDto.setDishId( dish.getDishId() );
        collectDishResultDto.setDishName( dish.getDishName() );
        collectDishResultDto.setDishImage( dish.getDishImage() );
        collectDishResultDto.setTags( dish.getTags() );
        collectDishResultDto.setPrice( dish.getPrice() );
        collectDishResultDto.setOnSaleStatus( dish.getOnSaleStatus() );

        return collectDishResultDto;
    }
}
