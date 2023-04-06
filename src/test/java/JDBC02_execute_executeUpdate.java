import java.sql.*;

public class JDBC02_execute_executeUpdate {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root","1234");
        Statement st = con.createStatement();

       /*
           ORNEK1: isciler tablosunu siliniz.
       */


        String dropQuery = "DROP TABLE isciler";

        // System.out.println(st.execute(dropQuery));


        if(!st.execute(dropQuery)){
        System.out.println("isciler tablosu silindi!");
        }

       /*
           ORNEK2: isciler adinda bir talo olusturunuz
           id int, birim VARCHAR(10), maas int.
       */

        String createTable = "CREATE TABLE isciler (id INT, birim VARCHAR(10), maas INT)";


        if(!st.execute(createTable)){
            System.out.println("isciler tablosu olusturuldu!");
        }


        /*
        ORNEK3: isciler tablosundan yeni bir kayit
              (80, 'ARGE', 40000) ekleyelim
         */

        String insertData = "INSERT INTO isciler VALUES(80, 'ARGE', 40000)";

        System.out.println("islemden etkilenen satir sayisi : " + st.executeUpdate(insertData));

         /*
        ORNEK4: isciler tablosuna birden fazla kayıt ekleyelim

              INSERT INTO isciler VALUES
              (70,'HR',5000),
              (60,'LAB',3000),
              (50,'ARGE',4000);
         */


        String [] queries ={"INSERT INTO isciler VALUES (70,'HR',5000), (60,'LAB',3000), (50,'ARGE',4000)"};


        int count=0;

        for (String each: queries) {
            count+=st.executeUpdate(each);

        }
        System.out.println(count + " satir eklendi!");

        // Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin
        // yavas yapilmasina yol acar. 1000 tane veri kaydi yapidigi dusunuldugunde
        // bu kotu bir yaklasimdir

        String [] queries2 ={"INSERT INTO isciler VALUES (10,'TEKNIK',3000), (20,'KANTIN',2000), (30,'ARGE',5000)"};

        for (String each: queries2) {
            st.addBatch(each);
        }

        st.executeBatch();

        System.out.println("satirlar eklendi");






         /*
        ORNEK5: isciler tablosunu goruntuleyin
         */

        System.out.println("=========== ISCILER TABLOSU ============ ");

        String selectQuery = "SELECT * FROM isciler";

        ResultSet iscilerTablosu = st.executeQuery(selectQuery);

        System.out.println(iscilerTablosu.getInt(1) + " " +
                           iscilerTablosu.getString(2) + " " +
                           iscilerTablosu.getInt(3));


        /*
        ORNEK6 : isciler tablosundaki maasi 5000'den az olan iscilerin maasina
        %10 zam yapiniz
         */

        String updateQuery ="UPDATE isciler, SET maas=maas*1.10 WHERE maas<5000";

        int satir=st.executeUpdate(updateQuery);
        System.out.println(satir + " satir guncellendi!");

         /*
        ORNEK7: isciler tablosunu goruntuleyin
         */

        System.out.println("=========== ISCILER TABLOSU MAAS ZAMLARİ ============ ");

        ResultSet iscilerTablosu2 = st.executeQuery(selectQuery);

        System.out.println(iscilerTablosu2.getInt(1) + " " +
                iscilerTablosu2.getString(2) + " " +
                iscilerTablosu2.getInt(3));






    }
}
