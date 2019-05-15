package czx.me.demo.repository;
import czx.me.demo.base.BaseRepository;
import czx.me.demo.entity.User;

import java.util.List;

/**
 * <pre>
 *     用户持久层
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2017/11/14
 */
public interface UserRepository extends BaseRepository<User, Long> {


	List<User> findByLastNameContainingOrFirstNameContaining(String lastName, String firstName);
}
