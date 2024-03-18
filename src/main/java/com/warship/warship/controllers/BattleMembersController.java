package com.warship.warship.controllers;

import com.warship.warship.WarshipApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.Properties;

@Controller
public class BattleMembersController {
    @Value("${db.path}")
    private String path;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    WarshipApplication warshipApplication = new WarshipApplication();
    @PostMapping("add_bms")
        public String add_bm(@RequestParam String battle_add, @RequestParam String warship_add, @RequestParam String status_add) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

            PreparedStatement pstmt;
            String warship_id = null;
            Date battle_date = null, warship_date_comission = null, warship_date_decomission = null;

            pstmt = con.prepareStatement("select * from BATTLES WHERE BATTLE_NAME=?");
            pstmt.setString(1, battle_add);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                battle_date = Date.valueOf(rs.getString(2));
            }

            pstmt = con.prepareStatement("select * from WARSHIPS WHERE NAME=?");
            pstmt.setString(1, warship_add);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                warship_date_comission = Date.valueOf(rs.getString(4));
                System.out.println(rs.getString(5));
                System.out.println(rs);
                if(rs.getString(5)!=null)
                    warship_date_decomission = Date.valueOf(rs.getString(5));
                warship_id = String.valueOf(rs.getString(1));
            }
            pstmt = con.prepareStatement("select * from BATTLE_MEMBERS WHERE WARSHIP_ID=?");
            pstmt.setString(1, warship_id);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
               return "redirect:/battle_members_error_warship";
            }
            if(warship_date_comission.before(battle_date) && (warship_date_decomission == null || warship_date_decomission.equals(battle_date) || warship_date_decomission.after(battle_date)))
            {
                pstmt = con.prepareStatement("INSERT INTO BATTLE_MEMBERS(BATTLE_NAME,WARSHIP_ID,STATUS) VALUES (?,?,?)");
                pstmt.setString(1, battle_add);
                pstmt.setString(2, warship_id);
                pstmt.setString(3, status_add);
                pstmt.execute();
                pstmt.close();
                con.close();
            }
            else return "redirect:/battle_members_error";


            return "redirect:/battle_members";
        }

    @PostMapping("edit_bms")
    public String edit_bm(@RequestParam String id_bm, @RequestParam String battle_edit, @RequestParam String warship_edit, @RequestParam String status_edit) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

        Statement st = con.createStatement();
        PreparedStatement pstmt;
        Date battle_date = null, warship_date_comission = null, warship_date_decomission = null;
        String warship_id = null;
        pstmt = con.prepareStatement("select * from BATTLES WHERE BATTLE_NAME=?");
        pstmt.setString(1, battle_edit);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next())
        {
            battle_date = Date.valueOf(rs.getString(2));
        }

        pstmt = con.prepareStatement("select * from WARSHIPS WHERE NAME=?");
        pstmt.setString(1, warship_edit);
        rs = pstmt.executeQuery();

        while (rs.next())
        {
            warship_date_comission = Date.valueOf(rs.getString(4));
            if (rs.getString(5)!=null)
            warship_date_decomission = Date.valueOf(rs.getString(5));
            warship_id = String.valueOf(rs.getString(1));

        }
        if(warship_date_comission.before(battle_date) && ( warship_date_decomission==null  || warship_date_decomission.equals(battle_date) ||warship_date_decomission.after(battle_date))) {
            pstmt = con.prepareStatement("UPDATE BATTLE_MEMBERS SET BATTLE_NAME=?, WARSHIP_ID=?, STATUS=? WHERE ID=?");
            pstmt.setString(1, battle_edit);
            pstmt.setString(2, warship_id);
            pstmt.setString(3, status_edit);
            pstmt.setString(4, id_bm);
            pstmt.execute();
            pstmt.close();
            con.close();
        }
        return "redirect:/battle_members";
    }

    @PostMapping("delete_bms")
    public String delete_bm(@RequestParam String delete_name) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

        Statement st = con.createStatement();
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("DELETE FROM BATTLE_MEMBERS WHERE ID=?");
        pstmt.setString(1, delete_name);
        pstmt.execute();
        pstmt.close();
        con.close();
        return "redirect:/battle_members";
    }

}