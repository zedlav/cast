/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cast.models;

import java.sql.SQLException;

/**
 *
 * @author Zedlav
 */
public interface Operation {
    public boolean Add() throws SQLException;
    public boolean Edit() throws SQLException;
    public boolean Delete() throws SQLException;
}
