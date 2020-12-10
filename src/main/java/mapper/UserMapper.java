package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pojo.DO.User;

import java.util.List;

public interface UserMapper {
    /**
     * 用户登录验证，无用户则返回0
     */
    @Select("select ifnull(max(uid),0) " +
            "from t_user " +
            "where uname = #{uname} and password = #{password} and role = #{role}")
    int selByUnamePwdRole(@Param("uname") String uname,
                          @Param("password") String password,
                          @Param("role") String role);

    /**
     * 存入（更新）PID
     */
    @Update("update t_user " +
            "set PID = #{PID} " +
            "where uid = #{uid}")
    int updPIDByUid(@Param("PID") String PID, @Param("uid") int uid);

    /**
     * 新增用户记录，自增的主键无需传入
     */
    @Insert("insert t_user (uname,password,role)  " +
            "values(#{uname},#{password},#{role})")
    int insUser(@Param("uname") String uname,
                @Param("password") String password,
                @Param("role") String role);

    /**
     * 根据用户名查找uid
     */
    @Select("select uid " +
            "from t_user " +
            "where uname = #{uname}")
    int selUidByUname(@Param("uname") String uname);

    @Select("select uid,uname,role,PID " +
            "from t_user")
    List<User> selAll();
}
