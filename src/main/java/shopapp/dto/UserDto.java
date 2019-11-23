package shopapp.dto;

import shopapp.models.UserEntity;

public class UserDto {
    private int id;
    private String name;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static UserDto MapFrom(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public UserEntity MapTo() {
        UserEntity user = new UserEntity();
        user.setName(this.getName());
        user.setPassword(this.getPassword());
        return user;
    }
}
