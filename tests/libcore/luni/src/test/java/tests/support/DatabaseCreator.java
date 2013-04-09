/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.support;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class DatabaseCreator {
    public static final int defaultInt = 2;

    public static final String defaultString = "string";

    public static final String defaultCharacter = "chr";

    public static final double defaultDouble = 0.1;

    public static final String TEST_TABLE1 = "table1";

    public static final String TEST_TABLE2 = "table2";

    public static final String TEST_TABLE3 = "table3";

    public static final String TEST_TABLE4 = "table4";

    public static final String SALESPEOPLE_TABLE = "Salespeople";

    public static final String CUSTOMERS_TABLE = "Customers";

    public static final String ORDERS_TABLE = "Orders";

    public static final String PARENT_TABLE = "test_names";

    public static final String FKSTRICT_TABLE = "test_strict";

    public static final String FKCASCADE_TABLE = "test_cascade";

    public static final String TEST_TABLE5 = "test";

    public static final String SIMPLE_TABLE1 = "simple_table1";

    public static final String SIMPLE_TABLE2 = "simple_table2";

    public static final String SIMPLE_TABLE3 = "simple_table3";

    public static final String CREATE_TABLE1 = "CREATE TABLE " + TEST_TABLE1
            + " (id INTEGER NOT NULL," + " field1 CHAR(100) DEFAULT NULL,"
            + " field2 DECIMAL " //+ defaultInt
            + " COMMENT 'field2_rem'," + " field3 DECIMAL," + " fkey INTEGER,"
            + " PRIMARY KEY (id) FOREIGN KEY (fkey) REFERENCES "
            + TEST_TABLE3 + "(fk))";

    public static final String CREATE_TABLE2 = "CREATE TABLE " + TEST_TABLE2
            + " ( " + "finteger integer NOT NULL, " + "ftext text, "
            + "fcharacter character (5), " + "fdecimal decimal (5,1), "
            + "fnumeric numeric (4,1), " + "fsmallint smallint, "
            + "ffloat float, " + "freal real, " + "fdouble double, "
            + "fdate date," + " ftime time, PRIMARY KEY (finteger))";

    public static final String CREATE_TABLE3 = "CREATE TABLE " + TEST_TABLE3
            + " (fk INTEGER NOT NULL," + "" + " PRIMARY KEY (fk))";

    public static final String CREATE_TABLE4 = "CREATE TABLE " + TEST_TABLE4
            + " (fk INTEGER NOT NULL," + " field1 CHAR(100) NOT NULL,"
            + " PRIMARY KEY (fk))";

    public static final String CREATE_TABLE5 = "CREATE TABLE " + TEST_TABLE5
            + "( testId INTEGER NOT NULL, testValue CHAR(200))";

    public static final String CREATE_TABLE_SALESPEOPLE = "CREATE TABLE "
            + SALESPEOPLE_TABLE + " (snum integer, sname character (10),"
            + " city character (10), comm real, PRIMARY KEY (snum))";

    public static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE "
            + CUSTOMERS_TABLE
            + " (cnum integer, cname character (10), city character (10),"
            + " rating integer, snum integer, PRIMARY KEY (cnum))";

    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE "
            + ORDERS_TABLE
            + " (onum integer, amt real, odate date, cnum integer,"
            + " snum integer, PRIMARY KEY (onum))";

    public static final String CREATE_TABLE_PARENT = "CREATE TABLE "
            + PARENT_TABLE + "(id INTEGER NOT NULL, "
            + "name CHAR(200), PRIMARY KEY(id))";

    public static final String CREATE_TABLE_FKSTRICT = "CREATE TABLE "
            + FKSTRICT_TABLE + "(id INTEGER NOT NULL," + "name_id INTEGER,"
            + "value CHAR(200), PRIMARY KEY(id), "
            + "CONSTRAINT fk1 FOREIGN KEY (name_id) " + "REFERENCES "
            + PARENT_TABLE + " (id) " + "ON DELETE RESTRICT "
            + "ON UPDATE RESTRICT)";

    public static final String CREATE_TABLE_FKCASCADE = "CREATE TABLE "
            + FKCASCADE_TABLE + "(id INTEGER NOT NULL," + "name_id INTEGER,"
            + "value CHAR(200), PRIMARY KEY(id), "
            + "CONSTRAINT fk2 FOREIGN KEY (name_id) " + "REFERENCES "
            + PARENT_TABLE + " (id) " + "ON DELETE CASCADE "
            + "ON UPDATE CASCADE)";

    public static final String CREATE_TABLE_SIMPLE1 = "CREATE TABLE "
            + SIMPLE_TABLE1 + "(id INTEGER NOT NULL," + "speed INTEGER,"
            + "size INTEGER)";

    public static final String CREATE_TABLE_SIMPLE2 = "CREATE TABLE "
            + SIMPLE_TABLE2 + "(id INTEGER NOT NULL," + "speed INTEGER,"
            + "size INTEGER)";

    public static final String CREATE_TABLE_SIMPLE3 = "CREATE TABLE "
            + SIMPLE_TABLE3 + "(id INTEGER NOT NULL," + "speed INTEGER)";

    public static final String DROP_TABLE1 = "DROP TABLE " + TEST_TABLE1;

    public static final String DROP_TABLE2 = "DROP TABLE " + TEST_TABLE2;

    public static final String DROP_TABLE3 = "DROP TABLE " + TEST_TABLE3;

    public static final String DROP_TABLE4 = "DROP TABLE " + TEST_TABLE4;

    public static final String DROP_TABLE5 = "DROP TABLE " + TEST_TABLE5;

    public static final String DROP_TABLE_CUSTOMERS = "DROP TABLE "
            + CUSTOMERS_TABLE;

    public static final String DROP_TABLE_ORDERS = "DROP TABLE " + ORDERS_TABLE;

    public static final String DROP_TABLE_SALESPEOPLE = "DROP TABLE "
            + SALESPEOPLE_TABLE;

    public static final String DROP_TABLE_PARENT = "DROP TABLE " + PARENT_TABLE;

    public static final String DROP_TABLE_FKSTRICT = "DROP TABLE "
            + FKSTRICT_TABLE;

    public static final String DROP_TABLE_FKCASCADE = "DROP TABLE "
            + FKCASCADE_TABLE;

    public static final String DROP_TABLE_SIMPLE1 = "DROP TABLE "
            + SIMPLE_TABLE1;

    public static final String DROP_TABLE_SIMPLE2 = "DROP TABLE "
            + SIMPLE_TABLE2;

    public static final String DROP_TABLE_SIMPLE3 = "DROP TABLE "
            + SIMPLE_TABLE3;

    public static final String INSERT_SALESPEOPLE1 = " INSERT INTO "
            + SALESPEOPLE_TABLE + " (snum, sname, city, comm) "
            + "VALUES (1001, 'Peel', 'London', .12)";

    public static final String INSERT_SALESPEOPLE2 = " INSERT INTO "
            + SALESPEOPLE_TABLE + " (snum, sname, city, comm) "
            + "VALUES (1002, 'Serres', 'SanJose', .13)";

    public static final String INSERT_SALESPEOPLE3 = " INSERT INTO "
            + SALESPEOPLE_TABLE + " (snum, sname, city, comm) "
            + "VALUES (1004, 'Motika', 'London', .11)";

    public static final String INSERT_SALESPEOPLE4 = " INSERT INTO "
            + SALESPEOPLE_TABLE + " (snum, sname, city, comm) "
            + "VALUES (1007, 'Rifkin', 'Barcelona', .15)";

    public static final String INSERT_SALESPEOPLE5 = " INSERT INTO "
            + SALESPEOPLE_TABLE + " (snum, sname, city, comm) "
            + "VALUES (1003, 'Axelrod', 'NewYork', .10)";

    public static final String INSERT_SALESPEOPLE6 = " INSERT INTO "
            + SALESPEOPLE_TABLE + " (snum, sname, city, comm) "
            + "VALUES (1013, 'Simpson', 'Kasan', .25)";

    public static final String INSERT_CUSTOMERS1 = " INSERT INTO "
            + CUSTOMERS_TABLE + " (cnum, cname, city, rating, snum)"
            + " VALUES (2001, 'Hoffman', 'London', 100, 1001)";

    public static final String INSERT_CUSTOMERS2 = " INSERT INTO "
            + CUSTOMERS_TABLE + " (cnum, cname, city, rating, snum) "
            + "VALUES (2002, 'Giovanni', 'Rome', 200, 1003)";

    public static final String INSERT_CUSTOMERS3 = " INSERT INTO "
            + CUSTOMERS_TABLE + " (cnum, cname, city, rating, snum) "
            + "VALUES (2003, 'Liu', 'SanJose', 200, 1002)";

    public static final String INSERT_CUSTOMERS4 = " INSERT INTO "
            + CUSTOMERS_TABLE + " (cnum, cname, city, rating, snum) "
            + "VALUES (2004, 'Grass', 'Berlin', 300, 1002)";

    public static final String INSERT_CUSTOMERS5 = " INSERT INTO "
            + CUSTOMERS_TABLE + " (cnum, cname, city, rating, snum) "
            + "VALUES (2006, 'Clemens', 'London', 100, 1001)";

    public static final String INSERT_CUSTOMERS6 = " INSERT INTO "
            + CUSTOMERS_TABLE + " (cnum, cname, city, rating, snum) "
            + "VALUES (2008, 'Cisneros', 'SanJose', 300, 1007)";

    public static final String INSERT_CUSTOMERS7 = " INSERT INTO "
            + CUSTOMERS_TABLE + " (cnum, cname, city, rating, snum) "
            + "VALUES (2007, 'Pereira', 'Rome', 100, 1004)";

    public static final String INSERT_ORDERS1 = " INSERT INTO " + ORDERS_TABLE
            + " (onum, amt, odate, cnum, snum) "
            + "VALUES (3001, 18.69, 10/03/1990, 2008, 1007)";

    public static final String INSERT_ORDERS2 = " INSERT INTO " + ORDERS_TABLE
            + " (onum, amt, odate, cnum, snum) "
            + "VALUES (3003, 767.19, 10/03/1990, 2001, 1001)";

    public static final String INSERT_ORDERS3 = " INSERT INTO " + ORDERS_TABLE
            + " (onum, amt, odate, cnum, snum) "
            + "VALUES (3002, 1900.10, 10/03/1990, 2007, 1004)";

    public static final String INSERT_ORDERS4 = " INSERT INTO " + ORDERS_TABLE
            + " (onum, amt, odate, cnum, snum) "
            + "VALUES (3005, 5160.45, 10/03/1990, 2003, 1002)";

    public static final String INSERT_ORDERS5 = " INSERT INTO " + ORDERS_TABLE
            + " (onum, amt, odate, cnum, snum) "
            + "VALUES (3006, 1098.16, 10/03/1990, 2008, 1007)";

    public static final String INSERT_ORDERS6 = " INSERT INTO " + ORDERS_TABLE
            + " (onum, amt, odate, cnum, snum) "
            + "VALUES (3009, 1713.23, 10/04/1990, 2002, 1003)";

    public static final String INSERT_ORDERS7 = " INSERT INTO " + ORDERS_TABLE
            + " (onum, amt, odate, cnum, snum) "
            + "VALUES (3007, 75.75, 10/04/1990, 2004, 1002)";

    public static final String INSERT_ORDERS8 = " INSERT INTO " + ORDERS_TABLE
            + " (onum, amt, odate, cnum, snum) "
            + "VALUES (3008, 4723.01, 10/05/1990, 2006, 1001)";

    public static final String INSERT_ORDERS9 = " INSERT INTO " + ORDERS_TABLE
            + " (onum, amt, odate, cnum, snum) "
            + "VALUES (3010, 1309.95, 10/06/1990, 2004, 1002)";

    public static final String INSERT_ORDERS10 = " INSERT INTO " + ORDERS_TABLE
            + " (onum, amt, odate, cnum, snum) "
            + "VALUES (3011, 9891.88, 10/06/1990, 2006, 1001)";

    public static void fillParentTable(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        statement
                .execute("INSERT INTO " + PARENT_TABLE + " VALUES(1, 'test1')");
        statement.execute("INSERT INTO " + PARENT_TABLE + " VALUES(2,'test2')");
        statement
                .execute("INSERT INTO " + PARENT_TABLE + " VALUES(3, 'test3')");
    }

    public static void fillFKStrictTable(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("INSERT INTO " + FKSTRICT_TABLE
                + " VALUES(1, 1, '1')");
        statement.execute("INSERT INTO " + FKSTRICT_TABLE
                + " VALUES(2, 2, '2')");
        statement.execute("INSERT INTO " + FKSTRICT_TABLE
                + " VALUES(3, 1, '3')");
    }

    public static void fillFKCascadeTable(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("INSERT INTO " + FKCASCADE_TABLE
                + " VALUES(1, 1, '1')");
        statement.execute("INSERT INTO " + FKCASCADE_TABLE
                + " VALUES(2, 2, '2')");
        statement.execute("INSERT INTO " + FKCASCADE_TABLE
                + " VALUES(3, 1, '3')");
    }

    public static void fillSimpleTable1(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        statement
                .execute("INSERT INTO " + SIMPLE_TABLE1 + " VALUES(1, 10, 20)");
        statement.execute("INSERT INTO " + SIMPLE_TABLE1 + " VALUES(2, 5, 7)");
    }

    public static void fillSimpleTable3(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("INSERT INTO " + SIMPLE_TABLE3 + " VALUES(1, 8)");
        statement.execute("INSERT INTO " + SIMPLE_TABLE3 + " VALUES(2, 6)");
        statement.execute("INSERT INTO " + SIMPLE_TABLE3 + " VALUES(3, 4)");
    }

    public static void fillSalesPeopleTable(Connection conn)
            throws SQLException {
        Statement statement = conn.createStatement();

        statement.execute(DatabaseCreator.INSERT_SALESPEOPLE1);
        statement.execute(DatabaseCreator.INSERT_SALESPEOPLE2);
        statement.execute(DatabaseCreator.INSERT_SALESPEOPLE3);
        statement.execute(DatabaseCreator.INSERT_SALESPEOPLE4);
        statement.execute(DatabaseCreator.INSERT_SALESPEOPLE5);
        statement.execute(DatabaseCreator.INSERT_SALESPEOPLE6);
    }

    public static void fillCustomersTable(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();

        statement.execute(DatabaseCreator.INSERT_CUSTOMERS1);
        statement.execute(DatabaseCreator.INSERT_CUSTOMERS2);
        statement.execute(DatabaseCreator.INSERT_CUSTOMERS3);
        statement.execute(DatabaseCreator.INSERT_CUSTOMERS4);
        statement.execute(DatabaseCreator.INSERT_CUSTOMERS5);
        statement.execute(DatabaseCreator.INSERT_CUSTOMERS6);
        statement.execute(DatabaseCreator.INSERT_CUSTOMERS7);
    }

    public static void fillOrdersTable(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();

        statement.execute(DatabaseCreator.INSERT_ORDERS1);
        statement.execute(DatabaseCreator.INSERT_ORDERS2);
        statement.execute(DatabaseCreator.INSERT_ORDERS3);
        statement.execute(DatabaseCreator.INSERT_ORDERS4);
        statement.execute(DatabaseCreator.INSERT_ORDERS5);
        statement.execute(DatabaseCreator.INSERT_ORDERS6);
        statement.execute(DatabaseCreator.INSERT_ORDERS7);
        statement.execute(DatabaseCreator.INSERT_ORDERS8);
        statement.execute(DatabaseCreator.INSERT_ORDERS9);
        statement.execute(DatabaseCreator.INSERT_ORDERS10);
    }

    public static void fillTestTable1(Connection conn, int numberOfRecords)
            throws SQLException {
        Statement statement = conn.createStatement();
        for (int id = 0; id < numberOfRecords; id++) {
            String value = DatabaseCreator.defaultString + id;
            String insertQuery = "INSERT INTO " + DatabaseCreator.TEST_TABLE1
                    + " (id, field1, field2, field3) VALUES(" + id + ", '"
                    + value + "', " + id + ", " + id + ")";
            statement.execute(insertQuery);
        }
    }

    public static void fillTestTable2(Connection conn, int startID, int endID,
            long time) throws SQLException {
        Statement statement = conn.createStatement();
        for (int id = startID; id <= endID; id++) {
            double value = id + DatabaseCreator.defaultDouble;
            String insertQuery = "INSERT INTO " + DatabaseCreator.TEST_TABLE2
                    + " (finteger, ftext, fcharacter, fdecimal, fnumeric,"
                    + " fsmallint, ffloat, freal, fdouble, fdate, ftime)"
                    + " VALUES (" + id + ", '" + DatabaseCreator.defaultString
                    + id + "'," + " '" + DatabaseCreator.defaultCharacter + id
                    + "', " + value + ", " + value + "," + value + ", " + value
                    + ", " + value + "," + value + ", '"
                    + new Date(time).toString() + "'," + " '"
                    + new Time(time).toString() + "')";
            statement.execute(insertQuery);
        }
    }

    public static void fillTestTable2(Connection conn, int numberOfRecords)
            throws SQLException {
        Statement statement = conn.createStatement();
        for (int id = 0; id < numberOfRecords; id++) {
            double value = id + DatabaseCreator.defaultDouble;
            String insertQuery = "INSERT INTO " + DatabaseCreator.TEST_TABLE2
                    + " (finteger, ftext, fcharacter, fdecimal, fnumeric,"
                    + " fsmallint, ffloat, freal, fdouble)" + " VALUES (" + id
                    + ", '" + DatabaseCreator.defaultString + id + "'," + " '"
                    + DatabaseCreator.defaultCharacter + id + "', " + value
                    + ", " + value + "," + value + ", " + value + ", " + value
                    + "," + value + ")";
            statement.execute(insertQuery);
        }
    }

    public static void fillTestTable4(Connection conn, int numberOfRecords)
            throws SQLException {
        Statement statement = conn.createStatement();
        for (int id = 0; id < numberOfRecords; id++) {
            String insertQuery = "INSERT INTO " + DatabaseCreator.TEST_TABLE4
                    + " (fk, field1) VALUES(" + id + ", \""
                    + DatabaseCreator.defaultString + id + "\")";
            statement.execute(insertQuery);
        }
    }

    public static void fillTestTable5(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("INSERT INTO " + TEST_TABLE5 + " VALUES(1, '0')");
        statement.execute("INSERT INTO " + TEST_TABLE5 + " VALUES(2, '3')");
        statement.execute("INSERT INTO " + TEST_TABLE5 + " VALUES(3, '4')");
    }
}
