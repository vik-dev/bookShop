package com.example.bookshop.models;

import com.example.bookshop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForSave {
    private double money;
    private Long id;

    public User convertToUser(){
        var user = new User();
        user.setId(this.getId());
        user.setMoney(this.getMoney());
        return user;
    }
}
