package cn.edu.szu.Bajie.common;

import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/7 22:07
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Integer total;
    private List<T> list;

    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<T>();
        result.setList(pageInfo.getRecords());

        result.setPageSize((int) pageInfo.getSize());
        result.setPageNum((int) pageInfo.getCurrent());
        result.setTotal((int) pageInfo.getTotal());
        result.setTotalPage((int) pageInfo.getPages());
        return result;
    }

    public static <T> CommonPage<T> toPage(
            Integer pageNum,
            Integer pageSize,
            Integer totalPage,
            Integer total,
            List<T> list
    ){
        return new CommonPage<>(
                pageNum,
                pageSize,
                totalPage,
                total,
                list
        );
    }

}
