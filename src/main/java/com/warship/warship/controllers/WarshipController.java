package com.warship.warship.controllers;

import com.warship.warship.WarshipApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

@Controller
public class WarshipController {
    @Value("${db.path}")
    private String path;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    WarshipApplication warshipApplication = new WarshipApplication();
    @PostMapping("add_warships")
        public String add_warship(@RequestParam String name_warship, @RequestParam String class_warship, @RequestParam Date add_com, @RequestParam String add_decom) throws SQLException, ParseException {

        Connection con = warshipApplication.getConnection(user,password,path);
            PreparedStatement pstmt;
            pstmt = con.prepareStatement("select * from WARSHIPS WHERE NAME=?");
            pstmt.setString(1, name_warship);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next())
            {
                return "redirect:/warships_error";
            }
            else {
                pstmt = con.prepareStatement("INSERT INTO WARSHIPS(NAME,CLASS,COMISSION_DATE) VALUES (?,?,?)");
                pstmt.setString(1, name_warship);
                pstmt.setString(2, class_warship);
                pstmt.setString(3, String.valueOf(add_com));
                pstmt.execute();
                if(add_decom!="")
                {
                    java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse(add_decom);
                    Date date2 = new Date(date.getTime());
                    pstmt = con.prepareStatement("UPDATE WARSHIPS SET DECOMISSION_DATE=? WHERE NAME=?");
                    pstmt.setString(1, String.valueOf(date2));
                    pstmt.setString(2, String.valueOf(name_warship));
                    pstmt.execute();
                }
                pstmt.close();
                con.close();
            }
            return "redirect:/warships";
        }

    @PostMapping("edit_warships")
    public String edit_warship(@RequestParam Integer edit_name,@RequestParam String edit_warship, @RequestParam String edit_class, @RequestParam Date edit_com, @RequestParam String edit_decom) throws SQLException, ParseException {
        Connection con = warshipApplication.getConnection(user,password,path);

        Statement st = con.createStatement();
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("UPDATE WARSHIPS SET NAME=?, CLASS=?, COMISSION_DATE=? WHERE ID=?");
        pstmt.setString(1, String.valueOf(edit_warship));
        pstmt.setString(2, String.valueOf(edit_class));
        pstmt.setString(3, String.valueOf(edit_com));
        pstmt.setString(4, String.valueOf(edit_name));
        pstmt.execute();
        if(edit_decom!="")
        {
            java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse(edit_decom);
            Date date2 = new Date(date.getTime());
            pstmt = con.prepareStatement("UPDATE WARSHIPS SET DECOMISSION_DATE=? WHERE ID=?");
            pstmt.setString(1, String.valueOf(date2));
            pstmt.setString(2, String.valueOf(edit_name));
            pstmt.execute();
        }
        else {
            pstmt = con.prepareStatement("UPDATE WARSHIPS SET DECOMISSION_DATE=? WHERE ID=?");
            pstmt.setString(1, null);
            pstmt.setString(2, String.valueOf(edit_name));
            pstmt.execute();
        }
        pstmt.close();
        con.close();
        return "redirect:/warships";
    }

    @PostMapping("delete_warships")
    public String delete_warship(@RequestParam String delete_name) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

        Statement st = con.createStatement();
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("DELETE FROM WARSHIPS WHERE ID=?");
        pstmt.setString(1, delete_name);
        pstmt.execute();
        pstmt.close();
        con.close();
        return "redirect:/warships";
    }

}