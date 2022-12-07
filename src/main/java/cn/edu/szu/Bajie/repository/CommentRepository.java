package cn.edu.szu.Bajie.repository;

import cn.edu.szu.Bajie.entity.CommentDish;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/12/2 12:29
 * @version 1.0
 */
public interface CommentRepository extends MongoRepository<CommentDish, String> {
}
