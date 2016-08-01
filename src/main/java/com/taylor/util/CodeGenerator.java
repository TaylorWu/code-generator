package com.taylor.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Taylor on 2016/7/29.
 * CodeGenerator
 */
public class CodeGenerator {

    public static void main(String[] args) {
        try {
            Configuration cfg = new Configuration();
            // 指定模板文件从何处加载的数据源，这里设置成一个文件目录
            ClassLoader classLoader = CodeGenerator.class.getClassLoader();
            URL url = classLoader.getResource("ftl");
            if (url == null) {
                return;
            }
            cfg.setDirectoryForTemplateLoading(new File(url.getFile()));
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            Properties properties = new Properties();
            properties.load(classLoader.getResourceAsStream("config.properties"));
            String packageName = properties.getProperty("packageName");
            String rootPath = properties.getProperty("rootPath");
            rootPath = rootPath + File.separator + "src/main/java" + File.separator;
            for (String dictName : packageName.split("\\.")) {
                rootPath += dictName + File.separator;
            }

            //Entity
            DatabaseEntityParser entityParser = new DatabaseEntityParser(properties.getProperty("tableName"));
            Entity entity = entityParser.parse();
            entity.setPackageName(packageName);
            entity.setEntityName(properties.getProperty("entityName"));
            entity.setIsTenant(properties.getProperty("isTenant"));
            Writer entityWriter = new OutputStreamWriter(new FileOutputStream(new File(rootPath + "entity" + File.separator + entity.getEntityName() + ".java")));
            Template entityTemplate = cfg.getTemplate("entity.ftl");
            entityTemplate.process(entity, entityWriter);
            entityWriter.flush();
            //Dto
            Writer dtoWriter = new OutputStreamWriter(new FileOutputStream(new File(rootPath + "remote" + File.separator + "dto" + File.separator + entity.getEntityName() + "Dto.java")));
            Template dtoTemplate = cfg.getTemplate("dto.ftl");
            dtoTemplate.process(entity, dtoWriter);
            dtoWriter.flush();
            //Dao
            Writer daoWriter = new OutputStreamWriter(new FileOutputStream(new File(rootPath + "dao" + File.separator + entity.getEntityName() + "Dao.java")));
            Template daoTemplate = cfg.getTemplate("dao.ftl");
            daoTemplate.process(entity, daoWriter);
            daoWriter.flush();
            //Manager
            Writer managerWriter = new OutputStreamWriter(new FileOutputStream(new File(rootPath + "service" + File.separator + entity.getEntityName() + "Manager.java")));
            Template managerTemplate = cfg.getTemplate("manager.ftl");
            managerTemplate.process(entity, managerWriter);
            managerWriter.flush();
            //Interface
            Writer interfaceWriter = new OutputStreamWriter(new FileOutputStream(new File(rootPath + "remote" + File.separator + "server" + File.separator + "interfaces" + File.separator + entity.getEntityName() + "Interface.java")));
            Template interfaceTemplate = cfg.getTemplate("interface.ftl");
            interfaceTemplate.process(entity, interfaceWriter);
            interfaceWriter.flush();
            //Implements
            Writer implementsWriter = new OutputStreamWriter(new FileOutputStream(new File(rootPath + "remote" + File.separator + "server" + File.separator + entity.getEntityName() + "Imp.java")));
            Template implementsTemplate = cfg.getTemplate("implements.ftl");
            implementsTemplate.process(entity, implementsWriter);
            implementsWriter.flush();
            //Client
            Writer clientWriter = new OutputStreamWriter(new FileOutputStream(new File(rootPath + "remote" + File.separator + "client" + File.separator + entity.getEntityName() + "Client.java")));
            Template clientTemplate = cfg.getTemplate("client.ftl");
            clientTemplate.process(entity, clientWriter);
            clientWriter.flush();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
