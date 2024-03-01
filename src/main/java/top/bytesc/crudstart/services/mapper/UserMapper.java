package top.bytesc.crudstart.services.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.bytesc.crudstart.models.User;
import top.bytesc.crudstart.utils.ThreadLocalUtil;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    public User findUserByName(String username);

    @Insert("INSERT INTO user(username, password, create_time, update_time)"+" " +
            "values(#{username},#{md5Pwd},now(),now())")
    public void add(String username, String md5Pwd);

    @Update("UPDATE user set email=#{email},username=#{username}, update_time=#{updateTime} "+" "+
    "WHERE id=#{id}")
    public void update(User user);

    @Update("UPDATE user set user_pic=#{url}, update_time=now()"+" "+
            "WHERE id=#{id}")
    public void updateUserPic(String url, Integer id);

    @Update("UPDATE user set password=#{pwd}, update_time=now()"+" "+
            "WHERE id=#{id}")
    public void updatePwd(String pwd, Integer id);
}
