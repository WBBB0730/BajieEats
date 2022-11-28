package cn.edu.szu.Bajie.converter;

import cn.edu.szu.Bajie.dto.result.CollectDishResultDto;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.Dish;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T20:02:14+0800",
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

        if ( dish.getDishId() != null ) {
            simpleDishResultDto.setDishId( dish.getDishId().intValue() );
        }
        simpleDishResultDto.setDishName( dish.getDishName() );
        simpleDishResultDto.setDishImage( dish.getDishImage() );
        simpleDishResultDto.setPrice( dish.getPrice() );

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
        dishDetailResultDto.setWinId( dish.getWinId() );
        dishDetailResultDto.setIngredients( dish.getIngredients() );
        dishDetailResultDto.setDeleteStatus( dish.getDeleteStatus() );
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

        if ( dish.getDishId() != null ) {
            collectDishResultDto.setDishId( dish.getDishId().intValue() );
        }
        collectDishResultDto.setDishName( dish.getDishName() );
        collectDishResultDto.setDishImage( dish.getDishImage() );
        collectDishResultDto.setPrice( dish.getPrice() );

        return collectDishResultDto;
    }
}
