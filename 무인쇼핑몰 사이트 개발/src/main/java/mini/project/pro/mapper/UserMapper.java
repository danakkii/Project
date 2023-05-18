package mini.project.pro.mapper;

import org.apache.ibatis.annotations.Mapper;

import mini.project.pro.model.User;

@Mapper
public interface UserMapper {

    public String getPw(String id);

    public User selectUser(String id);

    public void join(User user);

    
}
