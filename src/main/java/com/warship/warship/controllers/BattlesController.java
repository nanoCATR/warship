package com.warship.warship.controllers;

import com.warship.warship.WarshipApplication;
import com.warship.warship.models.Battle_members;
import com.warship.warship.models.Battles;
import com.warship.warship.models.Countries;
import com.warship.warship.models.Warships;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class BattlesController {
    @Value("${db.path}")
    private String path;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    WarshipApplication warshipApplication = new WarshipApplication();
    @PostMapping("add_battles")
        public String add_battle(@RequestParam String name_battle, @RequestParam String date_battle) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

            PreparedStatement pstmt;
            pstmt = con.prepareStatement("select * from BATTLES WHERE BATTLE_NAME=?");
            pstmt.setString(1, name_battle);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next())
            {

            }
            else {
                pstmt = con.prepareStatement("INSERT INTO BATTLES(BATTLE_NAME,BATTLE_DATE) VALUES (?,?)");
                pstmt.setString(1, name_battle);
                pstmt.setString(2, date_battle);
                pstmt.execute();
                pstmt.close();
                con.close();
            }
            return "redirect:/battles";
        }

    @PostMapping("edit_battles")
    public String edit_battle(@RequestParam String name_battle, @RequestParam Date battle_date) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

        Statement st = con.createStatement();
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("UPDATE BATTLES SET BATTLE_DATE=? WHERE BATTLE_NAME=?");
        pstmt.setString(1, String.valueOf(battle_date));
        pstmt.setString(2, name_battle);
        pstmt.execute();
        pstmt.close();
        con.close();
        return "redirect:/battles";
    }

    @PostMapping("delete_battles")
    public String delete_battle(@RequestParam String delete_name) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

        Statement st = con.createStatement();
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("DELETE FROM BATTLES WHERE BATTLE_NAME=?");
        pstmt.setString(1, delete_name);
        pstmt.execute();
        pstmt.close();
        con.close();
        return "redirect:/battles";
    }

}