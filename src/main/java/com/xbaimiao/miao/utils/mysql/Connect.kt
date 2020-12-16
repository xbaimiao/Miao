//package com.xbaimiao.miao.utils.mysql
//
//import com.mysql.jdbc.Connection
//import com.mysql.jdbc.Statement
//import java.sql.DriverManager
//import java.sql.ResultSet
//
//
//object Connect {
//    const val JDBC_DRIVER = "com.mysql.jdbc.Driver"
//    const val DB_URL = "jdbc:mysql://localhost:3306/RUNOOB"
//
//    const val USER = "roott"
//    const val PASS = "123456"
//
//    @JvmStatic
//    fun main(args: Array<String>) {
//        Class.forName(JDBC_DRIVER)
//        // 打开链接
//        println("连接数据库...")
//        val conn = DriverManager.getConnection(DB_URL, USER, PASS) as Connection
//        // 执行查询
//        println(" 实例化Statement对象...")
//        val stmt = conn.createStatement() as Statement
//        val sql2 = "CREATE TABLE websites(PersonID int,id varchar(255),name varchar(255))"
//        conn.prepareStatement(sql2)
//        val sql1 = "insert into websites(id,name) values('id','name')" //数据库操作语句（插入）
//        val pst = conn.prepareStatement(sql1) //用来执行SQL语句查询，对sql语句进行预编译处理
//
//        val sql = "SELECT id, name FROM websites"
//        val rs: ResultSet = stmt.executeQuery(sql)
//        // 展开结果集数据库
//        while (rs.next()) {
//            // 通过字段检索
//            val id = rs.getInt("id")
//            val name = rs.getString("name")
//            // 输出数据
//            print("ID: $id")
//            print(", 站点名称: $name")
//            print("\n")
//        }
//        // 完成后关闭
//        rs.close()
//        stmt.close()
//        conn.close()
//    }
//}