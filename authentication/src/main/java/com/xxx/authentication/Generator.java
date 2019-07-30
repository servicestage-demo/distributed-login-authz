package com.xxx.authentication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @Author wangqijun
 * @Date 14:18 2019-07-25
 **/
public class Generator {
  public void generator() throws Exception{
    List<String> warnings = new ArrayList<String>();
    boolean overwrite = true;
    /**指向逆向工程配置文件*/
    File configFile = new File("generatorConfig.xml");
    ConfigurationParser parser = new ConfigurationParser(warnings);
    Configuration config = parser.parseConfiguration(configFile);
    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
        callback, warnings);
    myBatisGenerator.generate(null);
  }
  public static void main(String[] args) throws Exception {
    try {
      Generator generatorSqlmap = new Generator();
      generatorSqlmap.generator();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
