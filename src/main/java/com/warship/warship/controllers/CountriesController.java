package com.warship.warship.controllers;

import com.warship.warship.WarshipApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.Properties;

@Controller
public class CountriesController {
    @Value("${db.path}")
    private String path;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    WarshipApplication warshipApplication = new WarshipApplication();
    @PostMapping("add_countries")
        public String add_countrie(@RequestParam String name_country, @RequestParam String name_side) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

            PreparedStatement pstmt;
                pstmt = con.prepareStatement("INSERT INTO COUNTRIES(NAME,SIDE) VALUES (?,?)");
                pstmt.setString(1, name_country);
                pstmt.setString(2, name_side);
                pstmt.execute();
                pstmt.close();
                con.close();

            return "redirect:/countries";
        }

    @PostMapping("edit_countries")
    public String edit_country(@RequestParam String edit_name, @RequestParam String edit_side) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

        Statement st = con.createStatement();
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("UPDATE COUNTRIES SET SIDE=? WHERE NAME=?");
        pstmt.setString(1, edit_side);
        pstmt.setString(2, edit_name);
        pstmt.execute();
        pstmt.close();
        con.close();
        return "redirect:/countries";
    }

    @PostMapping("delete_countries")
    public String delete_country(@RequestParam String delete_name) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

        Statement st = con.createStatement();
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("DELETE FROM COUNTRIES WHERE NAME=?");
        pstmt.setString(1, delete_name);
        pstmt.execute();
        pstmt.close();
        con.close();
        return "redirect:/countries";
    }

}