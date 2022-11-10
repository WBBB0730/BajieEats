package cn.edu.szu.Bajie.converter;

import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.entity.Canteen;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-11T00:00:53+0800",
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
        canteenDetailResultDto.setRecommendStatus( canteen.getRecommendStatus() );
        canteenDetailResultDto.setDeleteStatus( canteen.getDeleteStatus() );
        canteenDetailResultDto.setSort( canteen.getSort() );
        canteenDetailResultDto.setScore( canteen.getScore() );
        canteenDetailResultDto.setIsOpening( canteen.getIsOpening() );
        canteenDetailResultDto.setCreateTime( canteen.getCreateTime() );
        canteenDetailResultDto.setUpdateTime( canteen.getUpdateTime() );

        return canteenDetailResultDto;
    }
}
