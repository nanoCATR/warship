package com.warship.warship.controllers;

import com.warship.warship.Config;
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
import java.util.*;

@Controller
public class MainController {
    WarshipApplication warshipApplication = new WarshipApplication();
    @Value("${db.path}")
    private String path;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;


    @GetMapping(value = {"/", "battles"})
    public String home(Model model) throws SQLException {

        Connection con = warshipApplication.getConnection(user,password,path);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from BATTLES");

        List<Battles> battles = new ArrayList<Battles>();
        while (rs.next())
        {
            Battles battle = new Battles();
            battle.setBATTLE_NAME(rs.getString(1));
            battle.setBATTLE_DATE(rs.getString(2));
            battles.add(battle);
        }
        model.addAttribute("rows", battles);
        con.close();
        return "battles";

    }

    public String getName(Connection con, String id) throws SQLException{
        String name = null;
        PreparedStatement pstmt1;
        pstmt1 = con.prepareStatement("select * from WARSHIPS WHERE ID=?");
        pstmt1.setString(1, id);
        ResultSet rs1 = pstmt1.executeQuery();

        if (rs1.next())
        {
            name = rs1.getString(2);
        }
        return name;
    }

    @GetMapping("/battle_members")
    public String battle_members(Model model) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);
        Map<String, String> names=new HashMap<String,String>();


        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from WARSHIPS");
        while(rs.next())
        {
            names.put(rs.getString(1),rs.getString(2));
        }

        rs = st.executeQuery("select * from BATTLE_MEMBERS");
        List<Battle_members> battle_members = new ArrayList<Battle_members>();
        List<Battles> battles = new ArrayList<Battles>();
        List<Warships> warships = new ArrayList<Warships>();
        while (rs.next())
        {
            Battle_members battle_member = new Battle_members();
            battle_member.setID(rs.getString(1));
            battle_member.setBATTLE_NAME(rs.getString(2));
            String id = rs.getString(3);
            battle_member.setWARSHIP_NAME(names.get(id));
            battle_member.setSTATUS(rs.getString(4));

            battle_members.add(battle_member);
        }

        rs = st.executeQuery("select * from BATTLES");
        while (rs.next())
        {
            Battles battle = new Battles();
            battle.setBATTLE_NAME(rs.getString(1));
            battles.add(battle);
        }

        rs = st.executeQuery("select * from WARSHIPS");
        while (rs.next())
        {
            Warships warship = new Warships();
            warship.setNAME(rs.getString(2));
            warships.add(warship);
        }

        model.addAttribute("rows", battle_members);
        model.addAttribute("battles", battles);
        model.addAttribute("warships", warships);
        con.close();
        return "battle_members";
    }

    @GetMapping("/countries")
    public String countries(Model model) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from COUNTRIES");

        List<Countries> countries = new ArrayList<Countries>();
        while (rs.next())
        {
            Countries Country = new Countries();
            Country.setNAME(rs.getString(1));
            Country.setSIDE(rs.getString(2));
            countries.add(Country);
        }
        model.addAttribute("rows", countries);
        con.close();
        return "countries";
    }

    @GetMapping("/warships")
    public String warships(Model model) throws SQLException {
        Connection con = warshipApplication.getConnection(user,password,path);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from WARSHIPS");

        List<Warships> warships = new ArrayList<Warships>();
        while (rs.next())
        {
            Warships warship = new Warships();
            warship.setID(rs.getInt(1));
            warship.setNAME(rs.getString(2));
            warship.setCLASS(rs.getString(3));
            warship.setCOMISSION_DATE(rs.getString(4));
            warship.setDECOMISSION_DATE(rs.getString(5));
            warships.add(warship);
        }
        rs.close();
        con.close();
        model.addAttribute("rows", warships);
        con.close();
        return "warships";
    }

    @GetMapping("/battle_members_error")
    public String bm_error(Model model) {

        return "battle_members_error";
    }
    @GetMapping("/battle_members_error_warship")
    public String bm_error_warships(Model model) {

        return "battle_members_error_warship";
    }
    @GetMapping("/warships_error")
    public String warship_error(Model model) {

        return "warships_error";
    }


}