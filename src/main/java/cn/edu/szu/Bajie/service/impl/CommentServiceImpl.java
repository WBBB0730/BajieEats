package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.common.CommonPage;
import cn.edu.szu.Bajie.converter.CommentConverter;
import cn.edu.szu.Bajie.dto.result.DishCommentResultDto;
import cn.edu.szu.Bajie.dto.result.UserCommentsResultDto;
import cn.edu.szu.Bajie.entity.*;
import cn.edu.szu.Bajie.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.mapper.CommentMapper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author Whitence
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2022-11-09 15:12:39
*/
@Service
@AllArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{


    private UserService userService;

    private DishService dishService;

    private CanteenService canteenService;

    private WindowsService windowsService;

    private CommentUrlService commentUrlService;

    private CommentConverter commentConverter;

    @Override
    public List<UserCommentsResultDto> getUserComments(String userId) {
        // 获取评论列表
        return this.list(
                new LambdaQueryWrapper<Comment>()
                        .eq(Comment::getOpenId,userId)
                        .orderByDesc(Comment::getCreateTime)
        )
                .stream()
                .map(comment -> {
                    // 获取评论信息
                    UserCommentsResultDto userComment = commentConverter.comment2UserComment(comment);
                    // 获取用户信息
                    User user = userService.getOne(
                            new LambdaQueryWrapper<User>()
                                    .eq(User::getOpenId, userId)
                    );
                    // 评论url
                    List<String> commentUrls = commentUrlService.list(
                            new LambdaQueryWrapper<CommentUrl>()
                                    .eq(CommentUrl::getCommentId, userComment.getCommentId())
                    )
                            .stream()
                            .map(CommentUrl::getUrl)
                            .collect(Collectors.toList());

                    // 菜品信息
                    Dish dish = dishService.getOne(
                            new LambdaQueryWrapper<Dish>()
                                    .eq(Dish::getDishId, userComment.getDishId())
                    );

                    // 窗口信息
                    Windows windows = windowsService.getOne(
                            new LambdaQueryWrapper<Windows>()
                                    .eq(Windows::getWinId, dish.getWinId())
                    );
                    // 餐厅信息
                    Canteen canteen = canteenService.getOne(
                            new LambdaQueryWrapper<Canteen>()
                                    .eq(Canteen::getCanteenId, windows.getCanteenId())
                    );
                    // 准备结果类
                    userComment.setAvatarUrl(user.getAvatarUrl());
                    userComment.setNickName(user.getNickName());

                    userComment.setCommentUrls(commentUrls);

                    userComment.setDishName(dish.getDishName());
                    userComment.setDishImage(dish.getDishImage());

                    userComment.setWinName(windows.getWinName());

                    userComment.setCanteenName(canteen.getCanteenName());
                    userComment.setCanteenAddress(canteen.getCanteenAddress());

                    return userComment;
                }).collect(Collectors.toList());

    }

    @Override
    public CommonPage<DishCommentResultDto> getDishComments(Integer dishId,Integer pageIndex,Integer pageSize) {

        // 查询条件
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<
                >();
        wrapper.eq(Comment::getDishId,dishId)
                .orderByDesc(Comment::getCreateTime);

        //
        CommonPage<DishCommentResultDto> commonPage = new CommonPage<>();
        // 分页获取
        Page<Comment> page = new Page<>(pageIndex, pageSize);
        this.page(page, wrapper);

        // 根据分页获取的数据转换为结果类型
        List<DishCommentResultDto> commentResultDtos = page.getRecords()
                .stream()
                .map(comment -> {
                    DishCommentResultDto resultDto = commentConverter.comment2DishComment(comment);
                    // 获取用户信息
                    User user = userService.getOne(
                            new LambdaQueryWrapper<User>()
                                    .eq(User::getOpenId, comment.getOpenId())
                    );
                    // 评论照片
                    List<String> urls = commentUrlService.list(
                                    new LambdaQueryWrapper<CommentUrl>()
                                            .eq(CommentUrl::getCommentId, comment.getCommentId())
                            )
                            .stream()
                            .map(CommentUrl::getUrl)
                            .collect(Collectors.toList());

                    resultDto.setCommentUrls(urls);
                    resultDto.setNickName(user.getNickName());
                    resultDto.setAvatarUrl(user.getAvatarUrl());

                    return resultDto;
                }).collect(Collectors.toList());

        // 准备最终结果
        commonPage.setList(commentResultDtos);

        commonPage.setPageSize((int) page.getSize());
        commonPage.setPageNum((int) page.getCurrent());
        commonPage.setTotal((int) page.getTotal());
        commonPage.setTotalPage((int) page.getPages());

        return commonPage;
    }


}




