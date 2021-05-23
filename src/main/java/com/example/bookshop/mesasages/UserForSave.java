package com.example.bookshop.mesasages;

import com.example.bookshop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForSave {
    private double money;
    private String name;

    public User convertToUser(){
        var user = new User();
        user.setName(this.getName());
        user.setMoney(this.getMoney());
        return user;
    }
}
