package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Canteen;
import java.util.List;

import cn.edu.szu.Bajie.entity.CanteenUrl;
import cn.edu.szu.Bajie.entity.Windows;
import lombok.Data;

@Data
public class CanteenDetailResultDto extends Canteen {

    List<Windows> windows;

    List<CanteenUrl> canteenUrls;


}
