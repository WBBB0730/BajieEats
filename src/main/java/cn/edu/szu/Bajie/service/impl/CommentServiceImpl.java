package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.common.CommonPage;
import cn.edu.szu.Bajie.common.OpenIdHolder;
import cn.edu.szu.Bajie.converter.CommentConverter;
import cn.edu.szu.Bajie.dto.add.CommentAddDto;
import cn.edu.szu.Bajie.dto.result.DishCommentsResultDto;
import cn.edu.szu.Bajie.dto.result.UserCommentsResultDto;
import cn.edu.szu.Bajie.entity.CommentDish;
import cn.edu.szu.Bajie.entity.User;
import cn.edu.szu.Bajie.repository.CommentRepository;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.edu.szu.Bajie.service.DishService;
import cn.edu.szu.Bajie.service.UserService;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Comment;
import cn.edu.szu.Bajie.service.CommentService;
import cn.edu.szu.Bajie.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author Whitence
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2022-11-28 21:23:23
*/
@Service
@AllArgsConstructor
public class CommentServiceImpl
    implements CommentService{

    private CommentRepository commentRepository;

    private CanteenService canteenService;

    private UserService userService;

    private CommentConverter commentConverter;

    @Override
    public void commentDish(CommentAddDto dto) {

        CommentDish commentDish = new CommentDish();
        commentDish.setCommentId(IdUtil.getSnowflakeNextId());
        commentDish.setDishId(dto.getDishId());
        commentDish.setCommentUrls(dto.getCommentUrls());
        commentDish.setOpenId(OpenIdHolder.openIdThreadLocal.get());
        commentDish.setContent(dto.getContent());
        commentDish.setScore(dto.getScore());

        commentRepository.save(commentDish);
    }



    @Override
    public CommonPage<DishCommentsResultDto> getDishComments(Long dishId, Integer page, Integer pageSize) {
        // 准备查询条件
        CommentDish comment = CommentDish.builder()
                .dishId(dishId)
                .deleted(false)
                .visible(true)
                .build();
        // 分页获取菜品信息
        Page<CommentDish> commentDishes = commentRepository.findAll(
                Example.of(comment),
                PageRequest.of(
                        page-1,
                        pageSize,
                        Sort.Direction.DESC,
                        "createTime"
                )
        );

        List<DishCommentsResultDto> resultDtoList = commentDishes
                .get()
                .map(commentDish -> {
                    // 评论信息
                    DishCommentsResultDto resultDto = commentConverter.comment2DishComment(commentDish);
                    // 用户信息
                    User user = userService.getUserInfo(resultDto.getOpenId());

                    resultDto.setNickName(user.getNickName());
                    resultDto.setAvatarUrl(user.getAvatarUrl());
                    // 设置是否点赞
                    resultDto.setIsLiked(
                            resultDto.getLikeUsers().contains(OpenIdHolder.openIdThreadLocal.get())?1:0
                    );
                    resultDto.setLikeUsers(null);

                    return resultDto;
                })
                .collect(Collectors.toList());


        return CommonPage.toPage(
                page,
                pageSize,
                commentDishes.getTotalPages(),
                (int)commentDishes.getTotalElements(),
                resultDtoList
        );
    }

    @Override
    public CommonPage<UserCommentsResultDto> getUserComments(Integer page, Integer pageSize) {

        // 分页获取用户评论信息
        Page<CommentDish> commentDishes = commentRepository.findAll(
                // 查询条件
                Example.of(CommentDish.builder()
                        .openId(OpenIdHolder.openIdThreadLocal.get())
                        .deleted(false)
                        .visible(true)
                        .build()),
                // 分页以及排序规则
                PageRequest.of(
                        page-1,
                        pageSize,
                        Sort.Direction.DESC,
                        "createTime"
                )
        );
        //
        List<UserCommentsResultDto> resultDtoList = commentDishes
                .get()
                .map(commentDish -> {
                    // 准备结果信息
                    UserCommentsResultDto resultDto = commentConverter.comment2UserComment(commentDish);

                    User user = userService.getUserInfo(resultDto.getOpenId());
                    // 用户信息
                    resultDto.setNickName(user.getNickName());
                    resultDto.setAvatarUrl(user.getAvatarUrl());
                    resultDto.setLikeUsers(null);
                    // 准备菜品信息
                    resultDto.setDishInfo(canteenService.getSimpleDishResult(commentDish.getDishId()));

                    return resultDto;
                })
                .collect(Collectors.toList());


        return CommonPage.toPage(
                page,
                pageSize,
                commentDishes.getTotalPages(),
                (int)commentDishes.getTotalElements(),
                resultDtoList
        );

    }



    @Override
    public void doLike(Long commentId) {
        // 获取评论信息
        Optional<CommentDish> dishOptional = commentRepository.findById(commentId + "");
        if(!dishOptional.isPresent()){
            return;
        }

        CommentDish commentDish = dishOptional.get();

        String userId = OpenIdHolder.openIdThreadLocal.get();
        // 是否点赞
        boolean hasDone = commentDish.getLikeUsers().stream().anyMatch(userId::equals);
        // 若点赞则取消，未点赞则点赞
        if(hasDone){
            commentDish.getLikeUsers().remove(userId);
            commentDish.setLikes(commentDish.getLikes()-1);
        }else{
            commentDish.getLikeUsers().add(userId);
            commentDish.setLikes(commentDish.getLikes()+1);
        }
        // 更新
        commentRepository.save(commentDish);

    }
}




