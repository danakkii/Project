package mini.project.pro.mapper;


import org.apache.ibatis.annotations.Mapper;

import mini.project.pro.model.User;

@Mapper
public interface RecaptchaMapper {
    public void join(User user);

    public String getPw(String id);

    public User selectUser(String id);
}
