package cn.edu.szu.Bajie.converter;

import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.dto.result.CollectCanteenResultDto;
import cn.edu.szu.Bajie.dto.result.CollectDishResultDto;
import cn.edu.szu.Bajie.dto.result.CollectWindowResultDto;
import cn.edu.szu.Bajie.entity.Canteen;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T20:02:14+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class CanteenConverterImpl implements CanteenConverter {

    @Override
    public CanteenDetailResultDto canteen2canteenDetail(Canteen canteen) {
        if ( canteen == null ) {
            return null;
        }

        CanteenDetailResultDto canteenDetailResultDto = new CanteenDetailResultDto();

        canteenDetailResultDto.setCanteenId( canteen.getCanteenId() );
        canteenDetailResultDto.setCanteenName( canteen.getCanteenName() );
        canteenDetailResultDto.setMainImage( canteen.getMainImage() );
        canteenDetailResultDto.setCanteenAddress( canteen.getCanteenAddress() );
        canteenDetailResultDto.setCanteenPositionLongitude( canteen.getCanteenPositionLongitude() );
        canteenDetailResultDto.setCanteenPositionLatitude( canteen.getCanteenPositionLatitude() );
        canteenDetailResultDto.setOpeningTime( canteen.getOpeningTime() );
        canteenDetailResultDto.setDeleteStatus( canteen.getDeleteStatus() );
        canteenDetailResultDto.setCreateTime( canteen.getCreateTime() );
        canteenDetailResultDto.setUpdateTime( canteen.getUpdateTime() );

        return canteenDetailResultDto;
    }

    @Override
    public CollectCanteenResultDto canteen2collectCanteen(Canteen canteen) {
        if ( canteen == null ) {
            return null;
        }

        CollectCanteenResultDto collectCanteenResultDto = new CollectCanteenResultDto();

        if ( canteen.getCanteenId() != null ) {
            collectCanteenResultDto.setCanteenId( canteen.getCanteenId().intValue() );
        }
        collectCanteenResultDto.setCanteenName( canteen.getCanteenName() );
        collectCanteenResultDto.setMainImage( canteen.getMainImage() );
        collectCanteenResultDto.setCanteenAddress( canteen.getCanteenAddress() );
        collectCanteenResultDto.setCanteenPositionLongitude( canteen.getCanteenPositionLongitude() );
        collectCanteenResultDto.setCanteenPositionLatitude( canteen.getCanteenPositionLatitude() );

        return collectCanteenResultDto;
    }

    @Override
    public CollectWindowResultDto canteen2collectWindow(Canteen canteen) {
        if ( canteen == null ) {
            return null;
        }

        CollectWindowResultDto collectWindowResultDto = new CollectWindowResultDto();

        collectWindowResultDto.setCanteenName( canteen.getCanteenName() );
        collectWindowResultDto.setCanteenAddress( canteen.getCanteenAddress() );
        collectWindowResultDto.setCanteenPositionLongitude( canteen.getCanteenPositionLongitude() );
        collectWindowResultDto.setCanteenPositionLatitude( canteen.getCanteenPositionLatitude() );

        return collectWindowResultDto;
    }

    @Override
    public void canteen2collectDish(Canteen canteen, CollectDishResultDto resultDto) {
        if ( canteen == null ) {
            return;
        }

        resultDto.setCanteenName( canteen.getCanteenName() );
        resultDto.setCanteenAddress( canteen.getCanteenAddress() );
        resultDto.setCanteenPositionLongitude( canteen.getCanteenPositionLongitude() );
        resultDto.setCanteenPositionLatitude( canteen.getCanteenPositionLatitude() );
    }
}
