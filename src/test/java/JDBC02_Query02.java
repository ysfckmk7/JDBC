import java.sql.*;

public class JDBC02_Query02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root","1234");
        Statement st = con.createStatement();

        System.out.println("========ORNEK1============");
    /*
        ORNEK1 : Id'si 1'den buyuk firmalarin ismini ve
        iletisim_isim'ini isim ters sirali yazdirin

    */

        /*
        CREATE TABLE firmalar(
        id INT,
        isim VARCHAR(11),
        iletisim_isim VARCHAR(33),
        CONSTRAINT firmalar_pk PRIMARY KEY (id,isim)
        );

        INSERT INTO firmalar VALUES
        (1,'ACB','Ayse Can'),
        (2,'RDB','Ayse Gulmez'),
        (3,'Kmn','Veli Gul
        (4,'FDS','Veli Gul');

         */

       String selectquery="SELECT isim,iletisim_isim " +
                          "FROM firmalar " +
                          "WHERE id>1 " +
                          "ORDER BY isim DESC ";

       ResultSet data=st.executeQuery(selectquery);

       while (data.next()){

           System.out.println(data.getString("isim") + " " +
                              data.getString("iletisim_isim") + " ");
       }


        System.out.println("========ORNEK2============");

    /*
        ORNEK2 : Iletisim isminde 'li' ıceren firmalarin id'lerini ve isim'ini
                  id sirali yazdirin

    */
        String selectquery3="SELECT id, isim FROM firmalar WHERE iletisim_isim LIKE '%li%' ORDER BY id";

        ResultSet data2= st.executeQuery(selectquery3);

        while (data2.next()){

            System.out.println(data2.getInt("id") + " " +
                    data2.getString("isim"));
        }

        con.close();
        st.close();
        data.close();
        data2.close();
















    }
}

