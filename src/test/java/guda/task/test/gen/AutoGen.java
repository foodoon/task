package guda.task.test.gen;

import guda.gen.DemoGen;

/**
 * Created by foodoon on 2014/7/28.
 */
public class AutoGen {

    private static String jdbcPath = "C:\\Users\\well\\.m2\\repository\\mysql\\mysql-connector-java\\5.1.9\\mysql-connector-java-5.1.9.jar";

    private static String jdbUrl = "jdbc:mysql://127.0.0.1:3306/task?useUnicode=true&characterEncoding=utf8";

    public static void main(String[] args){
        DemoGen demoGen = new DemoGen();
        demoGen.setJdbcPassword("");
        demoGen.setJdbcUsername("root");
        demoGen.setJdbcPath(jdbcPath);
        demoGen.setJdbUrl(jdbUrl);
        demoGen.setDriverClass("com.mysql.jdbc.Driver");
        String baseDir = (String)System.getProperties().get("user.dir");
        demoGen.genDAOWithDir("taobao_item", "task", "guda",baseDir);
        demoGen.genDaoXMLWithDir("taobao_item","task","guda",baseDir);
     //   demoGen.genBizWithDir(baseDir,"user_score_stat","task","guda");
//        demoGen.genActionWithDir(baseDir,"account_detail","task","guda","/webapps/template/");





    }
}
