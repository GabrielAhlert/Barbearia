/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;


import conexao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Windows
 */
public class Teste {
    public static void main(String[] args) throws JRException, SQLException, ClassNotFoundException {

                Connection conn = Conexao.getConexao();


            String str = "EstoqueProdutos.jasper";

            JasperPrint jp = JasperFillManager.fillReport(str, null, conn);

            JasperViewer jv = new JasperViewer(jp,false);
            jv.setVisible(true);
            System.out.println("Hey!");
        
        
        
        
    }
    
    
}
