/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mbgnb.core.actions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.mbgnb.core.filetype.*;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

public final class Generate implements ActionListener {

    Logger log = Logger.getLogger(Generate.class.getName());
    private final nbftDataObject context;

    public Generate(nbftDataObject context) {
        this.context = context;
    }

    public void actionPerformed(ActionEvent ev) {
        try {
            FileObject f = context.getPrimaryFile();
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            File configFile = new File(FileUtil.getFileDisplayName(f));
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            StringBuilder results = new StringBuilder();
            if(warnings.size()>0){
                for(int i=0;i<warnings.size();i++){
                    results.append(warnings.get(i));
                    results.append("\n");
                }
            }else{
                results.append("Success!!!");
            }


            NotifyDescriptor nd = new NotifyDescriptor.Message(results);
            DialogDisplayer.getDefault().notify(nd);
        } catch (Exception ex) {
            log.error(ex);
            NotifyDescriptor nd = new NotifyDescriptor.Message(ex.getClass().getName()+ ". " + ex.getMessage()+": " + ex.getCause());
            DialogDisplayer.getDefault().notify(nd);
        }
        
    }
}
